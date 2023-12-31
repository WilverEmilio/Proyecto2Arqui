'use strict'
const Sequelize     = require('sequelize');
const db = require("../../models");
const Clientes = db.clientes;
const moment = require('moment');
const axios = require('axios')
const { Op } = require("sequelize");

module.exports = {
    find (req, res) {
        return Clientes.findAll()
        .then(cuenta => res.status(200).send(cuenta))
        .catch(error => res.status(400).send(error))
    },

    findById (req, res) {
      let id = req.body.id
      return Clientes.findByPk(id)
      .then(cuenta => res.status(200).send(cuenta))
      .catch(error => res.status(400).send(error))
    }, //Consulta por medio de una llave primaria

    create (req, res) {
        //Crear
        //extraer datos de req.body
        let datos = req.body //Serializar los datos
        const datos_ingreso = { //Objeto
            nombre: datos.nombre,
            apellido: datos.apellido,
            puntos: datos.puntos,
            estado: datos.estado,
        };

        Clientes.create(datos_ingreso)
        .then(clientes => {
            res.send(clientes);
        })
        .catch(error => {
            console.log(error)
            return res.status(500).json({ error: 'Error al insertar' });
        });
    },

    update (req, res) {
        //Actualizar
        let datos = req.body
          Clientes.update(
            { //En crudo
                nombre: datos.nombre,
                apellido: datos.apellido,
                puntos: datos.puntos,
                estado: datos.estado,
            },
            { 
              where: { 
                id: datos.id 
              }
            }
          )
          .then(clientes => res.status(200).send('El registro ha sido actualizado'))
          .catch(error => {
            console.log(error)
            return res.status(500).json({ error: 'Error al actualizar' });
          })
    },

    async delete (req, res) {
        //Eliminar
        console.log(req.params.id)
        let id = req.params.id; //Serializamos el id
        try {
          //Busqueda de un objeto especifico por id
          const clientes = await Clientes.findByPk(id);
          //evaluamos si el objeto trajo algo
          if (!clientes) {
            return res.status(404).json({ error: 'Clientes no encontrado' });
          }
          //Si pasa este punto
          await clientes.destroy();
          return res.json({ message: 'Clientes eliminado correctamente' });
        } catch (error) {
          console.error('Error al eliminar clientes:', error);
          return res.status(500).json({ error: 'Error al eliminar clientes' });
        }
    },
}