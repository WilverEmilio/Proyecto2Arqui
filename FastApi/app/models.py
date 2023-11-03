from sqlalchemy import Column, Integer, String, DateTime
from .Conexion import Base

class User(Base):
    __tablename__ = 'clientes'  # Corregir la definición de __tablename__
    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String(255))
    apellido = Column(String(255))
    puntos = Column(Integer)
    create_ad = Column(DateTime)
    update_ad = Column(DateTime)
