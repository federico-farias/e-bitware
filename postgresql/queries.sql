select count(p.*) from productos p;


----------------------------------------------------------------------------------------------------
-- Mostrar el número de ventas de cada producto, ordenado de más a menos ventas.
----------------------------------------------------------------------------------------------------
select 
	v.producto,
	count(v.producto) as ventas 
from ventas v
group by v.producto
order by ventas desc
limit 10;

----------------------------------------------------------------------------------------------------
-- Obtener un informe completo de ventas, indicando el nombre del cajero que realizo la
-- venta, nombre y precios de los productos vendidos, y el piso en el que se encuentra la
-- máquina registradora donde se realizó la venta.
select
	count(c.nom_apels) as ventas,
	c.nom_apels as nombre_cajero ,
	p.nombre as producto,
	p.precio,
	mr.piso as piso_maquina
from ventas v
join productos p on p.codigo = v.producto
join maquinas_registradoras mr on mr.codigo = v.maquina
join cajeros c on c.codigo = v.cajero
group by 
	c.nom_apels,
	p.nombre,
	p.precio,
	mr.piso
order by ventas desc
limit 10;

----------------------------------------------------------------------------------------------------
-- Obtener las ventas totales realizadas en cada piso.
----------------------------------------------------------------------------------------------------
-- total de ventas (unidades) por piso
select
	mr.piso,
	count(v.maquina) as ventas
from ventas v
join maquinas_registradoras mr on mr.codigo = v.maquina
group by mr.piso;


-- total de ventas (en dinero) por piso
select
	mr.piso,
	sum(p.precio) as ventas
from ventas v
join maquinas_registradoras mr on mr.codigo = v.maquina
join productos p on p.codigo = v.producto
group by mr.piso
order by ventas desc;


----------------------------------------------------------------------------------------------------
-- Obtener el código y nombre de cada cajero junto con el importe total de sus ventas.
----------------------------------------------------------------------------------------------------
select
	c.codigo, 
	c.nom_apels,
	sum(p.precio) as total_ventas
from cajeros c
join ventas v on v.cajero = c.codigo
join productos p on p.codigo = v.producto
group by c.codigo, c.nom_apels;


----------------------------------------------------------------------------------------------------
-- Obtener el código y nombre de aquellos cajeros que hayan realizado ventas en pisos
-- cuyas ventas totales sean inferiores a los 5000 pesos.
----------------------------------------------------------------------------------------------------
select 
	c.codigo
	,c.nom_apels
from ventas v
join cajeros c on c.codigo = v.cajero
join maquinas_registradoras mr on mr.codigo = v.maquina
where mr.piso in (
	select
		mr2.piso
	from ventas v2
		join maquinas_registradoras mr2 on mr2.codigo = v2.maquina
		join productos p2 on p2.codigo = v2.producto
		group by mr2.piso
		having sum(p2.precio) < 231000
)
group by c.codigo, c.nom_apels;

--- Más óptimo
SELECT 
    c.codigo,
    c.nom_apels
FROM ventas v
JOIN cajeros c ON c.codigo = v.cajero
JOIN maquinas_registradoras mr ON mr.codigo = v.maquina
JOIN (
	select
		mr2.piso
	from ventas v2
		join maquinas_registradoras mr2 on mr2.codigo = v2.maquina
		join productos p2 on p2.codigo = v2.producto
		group by mr2.piso
		having sum(p2.precio) < 5000
) AS pisos_bajo_ingreso ON pisos_bajo_ingreso.piso = mr.piso
GROUP BY c.codigo, c.nom_apels;



