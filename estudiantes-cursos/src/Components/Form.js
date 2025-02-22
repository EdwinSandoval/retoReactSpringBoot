import axios from 'axios';
import React from 'react';

const Form = ({book, setBook,setListUpdated}) => {

    const handleChange = e => {
        setBook({
            ...book,
            [e.target.name]: e.target.value
        })
    }

    let {nombre, precio, descripcion,marca,categoria} = book

    const handleSubmit = (e) => {
        e.preventDefault();
        precio = parseFloat(precio)
        //validación de los datos
        if (nombre === '' || precio <= 0   || categoria === '' ) {
            alert('Todos los campos son obligatorios')
            return 
        }
        // consulta
        const requestInit = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(book)
        }
        fetch('http://localhost:8081/producto', requestInit)
        .then(res => res.text())
         
        setBook({
            nombre:'',
            precio:0.0,
            descripcion:'',
            marca:'',
            categoria:'',
            
        })
        setListUpdated(true)
        // cargarDatos();
        
    }

   

    return ( 
        <form onSubmit={(e)=>handleSubmit(e)}>

         <div className="row align-items-start">
       
            <div className="mb-3 col">
                <label htmlFor="nombre" className="form-label">Nombre</label>
                <input value={nombre} name="nombre" onChange={handleChange} autocomplete="off" maxLength="30" type="text" id="nombre" className="form-control" style={{textAlign:'left'}}/>
            </div>
            <div className="mb-3 col">
                <label htmlFor="precio" className="form-label">Precio</label>
                <input value={precio} name="precio" onChange={handleChange} autocomplete="off" maxLength="30" type="text" id="precio" className="form-control"/>
            </div>
         </div>
         <div className="row align-items-start">
             
            <div className="mb-3 col">
                <label htmlFor="descripcion" className="form-label">Descripcion</label>
                <input value={descripcion}  name="descripcion" onChange={handleChange} autocomplete="off" maxLength="100" type="text"  id="descripcion" className="form-control"/>
            </div>
            <div className="mb-3 col">
                <label htmlFor="marca" className="form-label">Marca</label>
                <input value={marca}  name="marca" onChange={handleChange} autocomplete="off" type="text" maxLength="8"  id="marca" className="form-control"/>
            </div>
        </div>
        <div className="row align-items-start">
            <div className="mb-4 col-6">
                <label htmlFor="categoria" className="form-label">Categoria</label>
                <input value={categoria}  name="categoria" onChange={handleChange} autocomplete="off" maxLength="50" type="text" id="categoria" className="form-control"/>
            </div>
           
        </div>
        
            <div style={{textAlign: 'center'}}>
                <button type="submit" className="btn btn-primary mb-4" >Agregar</button>
            </div>
            
        </form>

         
    );
}
 
export default Form;