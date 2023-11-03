import datetime
from typing import Optional
from pydantic import BaseModel

class User(BaseModel):
    id: Optional [int]
    nombre: str
    apellido: str
    puntos: int
    create_ad: datetime.datetime  # Corregir el tipo de dato
    update_ad: datetime.datetime  # Corregir el tipo de dato
    
    class Config:
        orm_mode = True
        
class CreateUser(BaseModel):
    nombre: str
    apellido: str
    puntos: int
    
    class Config: 
        orm_mode = True

class UserUpdate(BaseModel):

    puntos: str

    
    class Config:
        orm_mode = True
        
class Respuesta(BaseModel):
    mensaje: str


    