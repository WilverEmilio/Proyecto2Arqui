'use strict'
const Sequelize     = require('sequelize');
const db = require("../../models/Proyecto2/people");
const Transacciones = require('../../models').Transacciones;
const Clientes = db.clientes;
const Tanques = db.tanques;
const moment = require('moment');
const axios = require('axios')
const { Op } = require("sequelize");

module.exports = {
    find (req, res) {
        return Transacciones.findAll({
            include: {
                model: Tanques,
                attibutes: ['nivel_actual', 'tipo_gasolina']
            }
        })
        .then(cuenta => res.status(200).send(cuenta))
        .catch(error => res.status(400).send(error))
    },

    findById (req, res) {
        let id = req.body.id
        return Transacciones.findByPk(id)
        .then(cuenta => res.status(200).send(cuenta))
        .catch(error => res.status(400).send(error))
      }, //Consulta por medio de una llave primaria

      create(req, res) {
        // Extraer datos de req.body
        const datos = req.body;
    
        // Calcular el total
        const total = datos.cantidad_vendida * datos.precio_por_galon;
    
        // Crear la transacción con el total calculado
        const datos_ingreso = {
            cantidad_vendida: datos.cantidad_vendida,
            precio_por_galon: datos.precio_por_galon,
            empleado: datos.empleado,
            cliente_id: datos.cliente_id,
            tanque_id: datos.tanque_id,
            total: total,  // Aquí se asigna el total calculado
            estado: datos.estado,
        };
    
        // Crear la transacción en la base de datos
        Transacciones.create(datos_ingreso)
            .then(transaccion => {
                // Actualizar los puntos del cliente
                Clientes.findByPk(datos.cliente_id)
                    .then(cliente => {
                        if (cliente) {
                            // Calcular los nuevos puntos del cliente
                            const nuevosPuntos = cliente.puntos - total;
                            // Actualizar los puntos del cliente
                            cliente.update({ puntos: nuevosPuntos })
                                .then(() => {
                                    // Enviar la respuesta al cliente
                                    res.send(transaccion);
                                })
                                .catch(error => {
                                    console.log(error);
                                    res.status(500).json({ error: 'Error al actualizar los puntos del cliente' });
                                });
                        } else {
                            res.status(404).json({ error: 'Cliente no encontrado' });
                        }
                    })
                    .catch(error => {
                        console.log(error);
                        res.status(500).json({ error: 'Error al buscar el cliente' });
                    });
            })
            .catch(error => {
                console.log(error);
                res.status(500).json({ error: 'Error al insertar' });
            });
    },
    //actualizar
    update (req, res){
        let datos = req.body
        Transacciones.update(
            { //En crudo
                cantidad_vendida: datos.cantidad_vendida,
                precio_por_galon: datos.precio_por_galon,
                empleado: datos.empleado,
                cliente_id: datos.cliente_id,
                tanque_id: datos.tanque_id,
                estado: datos.estado,
                total: datos.total
            },
            { 
              where: { 
                id: datos.id 
              }
            }
          )
          .then(result => {
            res.status(200).json({
              message: "Actualizado correctamente",
              count: result
            });
          })
          .catch(error => {
            console.log(error)
            return res.status(500).json({ error: 'Error al actualizar' });
          });
    },

    async delete (req, res) {
        //Eliminar
        console.log(req.params.id)
        let id = req.params.id; //Serializamos el id
        try {
          //Busqueda de un objeto especifico por id
          const transacciones = await Transacciones.findByPk(id);
          //evaluamos si el objeto trajo algo
          if (!transacciones) {
            return res.status(404).json({ error: 'Transacciones no encontrado' });
          }
          //Si pasa este punto
          await transacciones.destroy();
          return res.json({ message: 'Transacciones eliminado correctamente' });
        } catch (error) {
          console.error('Error al eliminar cliente:', error);
          return res.status(500).json({ error: 'Error al eliminar cliente' });
        }
    },
}