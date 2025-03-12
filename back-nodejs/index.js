const express = require('express');
const multer = require('multer');
const csvParser = require('csv-parser');
const mongoose = require('mongoose');
const fs = require('fs');

// Conectar a MongoDB con autenticación
const DB_USER = 'root';
const DB_PASSWORD = 'example';
const DB_HOST = 'localhost'; // O la IP/URL del servidor de MongoDB
const DB_NAME = 'csvDB';

const mongoURI = `mongodb://${DB_USER}:${DB_PASSWORD}@${DB_HOST}:27017/${DB_NAME}?authSource=admin`;


const app = express();
const PORT = process.env.PORT || 3000;

// Configurar Multer para subir archivos
const upload = multer({ dest: 'uploads/' });

// Conectar a MongoDB
mongoose.connect(mongoURI).then(() => {
  console.log('Conectado a MongoDB');
}).catch(err => {
  console.error('Error al conectar a MongoDB', err);
});

// Definir el esquema y modelo de Mongoose
const DataSchema = new mongoose.Schema({
  column1: String,
  column2: String,
  column3: String,
});
const DataModel = mongoose.model('Data', DataSchema);

// Endpoint para subir archivo CSV
app.post('/upload', upload.single('file'), async (req, res) => {
  if (!req.file) {
    return res.status(400).json({ message: 'No se ha subido ningún archivo' });
  }

  const results = [];

  fs.createReadStream(req.file.path)
    .pipe(csvParser())
    .on('data', (data) => results.push(data))
    .on('end', async () => {
      try {
        await DataModel.insertMany(results);
        fs.unlinkSync(req.file.path); // Eliminar el archivo después de procesarlo
        res.json({ message: 'Datos guardados correctamente' });
      } catch (error) {
        res.status(500).json({ message: 'Error al guardar en la base de datos', error });
      }
    });
});

app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
