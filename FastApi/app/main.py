from typing import List
from fastapi import FastAPI
from fastapi.params import Depends
from fastapi.responses import RedirectResponse
from . import models,Schemas
from .Conexion import SessionLocal,engine
from sqlalchemy.orm import Session

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

def get_db():
    try:
        db = SessionLocal()
        yield db
    finally:
        db.close()

@app.get("/")        
def main():
    return RedirectResponse(url="/docs/")

@app.get('/usuarios/',response_model = List[Schemas.User])
def show_users(db:Session=Depends(get_db)):
    usuarios = db.query(models.User).all()
    return usuarios

@app.post('/usuarios/', response_model=Schemas.User)
def create_users(entrada: Schemas.CreateUser, db: Session = Depends(get_db)):
    usuario = models.User(**entrada.dict())
    db.add(usuario)
    db.commit()
    db.refresh(usuario)
    return usuario

@app.put('/usuarios/{id}',response_model = Schemas.User)
def update_users(id:int,entrada:Schemas.UserUpdate,db:Session=Depends (get_db)):
    usuarios = db.query(models.User).filter_by(id=id).first()
    usuarios.puntos=entrada.puntos
    db.add(usuarios)
    db.commit()
    db.refresh(usuarios)
    return usuarios

@app.delete('/usuarios/{id}',response_model = Schemas.Respuesta)
def delete_users(id:int, db:Session=Depends(get_db)):
    usuarios = db.query(models.User).filter_by(id=id).first()
    db.delete(usuarios)
    db.commit()
    respuesta = Schemas.Respuesta(mensaje = "Eliminado exitosamente")
    return respuesta