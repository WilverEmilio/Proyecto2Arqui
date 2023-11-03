'use strict'
const Sequelize     = require('sequelize');
const db = require("../../models");
const Tanques = db.tanques;
const moment = require('moment');
const axios = require('axios')
const { Op } = require("sequelize");

module.exports = {
    find(req, res) {
        return Tanques.findAll()
        .then(cuenta => res.status(200).send(cuenta))
        .catch(error => res.status(400).send(error))
    },

    findById (req, res) {
        let id = req.body.id
        return Tanques.findByPk(id)
        .then(cuenta => res.status(200).send(cuenta))
        .catch(error => res.status(400).send(error))
      }, //Consulta por medio de una llave primaria

    create (req, res) {
        //Crear
        //extraer datos de req.body
        let datos = req.body //Serializar los datos
        const datos_ingreso = { //Objeto
            capacidad: datos.capacidad,
            nivel_actual: datos.nivel_actual,
            ubicacion: datos.ubicacion,
            tipo_gasolina: datos.tipo_gasolina,
            estado: datos.estado,
        };

        Tanques.create(datos_ingreso)
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
          Tanques.update(
            { //En crudo
                capacidad: datos.capacidad,
                nivel_actual: datos.nivel_actual,
                ubicacion: datos.ubicacion,
                tipo_gasolina: datos.tipo_gasolina,
                estado: datos.estado,
            },
            { 
              where: { 
                id: datos.id 
              }
            }
          )
          .then(tanques => res.status(200).send('El registro ha sido actualizado'))
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
          const tanques = await Tanques.findByPk(id);
          //evaluamos si el objeto trajo algo
          if (!tanques) {
            return res.status(404).json({ error: 'Tanques no encontrado' });
          }
          //Si pasa este punto
          await tanques.destroy();
          return res.json({ message: 'Tanques eliminado correctamente' });
        } catch (error) {
          console.error('Error al eliminar tanques:', error);
          return res.status(500).json({ error: 'Error al eliminar tanques' });
        }
    },
}