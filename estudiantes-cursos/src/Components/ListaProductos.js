import React,{useState} from 'react';
import axios from "axios"
import {
    Modal,
    Button
  } from "reactstrap";

const BookList = ({book, setBook, books, setListUpdated}) => {
    
   
    const [student,setstudent]=useState([])
    const [list, setList] = useState(false)
 

  const [nombre,setNombre]=React.useState("")
  const [precio,setPrecio]=React.useState("")
  const [descripcion,setDescripcion]=React.useState("")
  const [marca,setMarca]=React.useState("")
  const [categoriaId,setCategoriaId]=React.useState("")
 

  const handleUpdate2= async (id)=>{
    
        const requestInit = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body:JSON.stringify( {
              nombre,
              precio,
              descripcion,
              marca,
              categoriaId
             
            }),
        }
       fetch('http://localhost:8081/producto/'+id, requestInit)
       setListUpdated(true)
       cerrarModalActualizar()
       setListUpdated(true)
    
  }

    const handleDelete = async (id,e) => {//elimina
        const requestInit = {
            method: 'DELETE'
        }
        await fetch('http://localhost:8081/producto/' + id, requestInit)
        .then(res => res.text())
        
        setListUpdated(true)
    }

  
    const verProducto = async (id,e) => {//elimina
       
        const respuesta=await axios.get('http://localhost:8081/producto/'+id)
       
        const producto = respuesta.data;
        // respuesta.data (res=>{
          setNombre(producto.nombre)
          setPrecio(producto.precio)
          setDescripcion(producto.descripcion)
          setMarca(producto.marca)
          setCategoriaId(producto.categoriaId)
          
        setstudent(respuesta.data)
        
        setList(true)
        
    }
      
    const cerrarModalActualizar = () => {
       setList(false)
       setListUpdated(true)
    };
  
    return (
      <React.Fragment>

        <table className="table">
          <thead>
            <tr>
              <th>Nombre Producto</th>
              <th>Precio</th>
              <th>Descripcion</th>
              <th>Marca</th>
              <th>Categoria</th>
              
             
            </tr>
          </thead>
          <tbody>
            {books.map((book) => (
              <tr key={book.id}>
                <td>{book.nombre}</td>
                <td>{book.precio}</td>
                <td>{book.descripcion}</td>
                <td>{book.marca}</td>
                <td>{book.categoriaId}</td>
                
                <td className="row">
                  <div className=" col">
                    <button
                      onClick={() => handleDelete(book.id)}
                      className="btn btn-danger"
                    >
                      Eliminar
                    </button>
                  </div>
                
                  <div className="col" >
                      <button onClick={() => verProducto(book.id)} className="btn btn-warning">
                        Ver</button>
                 </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {student?( <Modal isOpen={list}  >
         
            <div style={{textAlign: 'center',marginTop:'25px'}} >
              <h3  style={{textAlign: 'center',color:'black'}}>Detalle Producto</h3>
            </div>
          
          
         
        <div style={{margin:'15px'}}>
        <div className="row align-items-start">
       
          <div className="mb-3 col"  style={{margin:'15px'}}>
              <label htmlFor="nombre" className="form-label colorletra">Nombre</label>
              <input   name="nombre" onChange={(e)=>setNombre(e.target.value)} autocomplete="off" defaultValue={student.nombre} type="text"  className="form-control" style={{textAlign:'left'}} maxLength="30"/>
          </div>
          <div className="mb-3 col"  style={{margin:'15px'}}>
              <label htmlFor="precio" className="form-label colorletra">Precio</label>
              <input name="precio" onChange={(e)=>setPrecio(e.target.value)} autocomplete="off" maxLength="30" defaultValue={student.precio} type="text" id="precio" className="form-control"/>
          </div>
       </div>
       <div className="row align-items-start">
       
          <div className="mb-3 col"  style={{margin:'15px'}}>
              <label htmlFor="descripcion" className="form-label colorletra">Descripcion</label>
              <input name="descripcion" onChange={(e)=>setDescripcion(e.target.value)} autocomplete="off" defaultValue={student.descripcion} max="100" type="text" id="descripcion" className="form-control" style={{textAlign:'left'}}/>
          </div>
          <div className="mb-3 col"  style={{margin:'15px'}}>
              <label htmlFor="marca" className="form-label colorletra">Marca</label>
              <input name="marca" onChange={(e)=>setMarca(e.target.value)} autocomplete="off" maxLength="8" defaultValue={student.marca} type="text" id="marca" className="form-control"/>
          </div>
       </div>
       <div className="row align-items-start">
       
          <div className="mb-3 col-5 "  style={{margin:'15px'}}>
              <label htmlFor="categoriaId" className="form-label colorletra">Categoria</label>
              <input name="categoriaId" onChange={(e)=>setCategoriaId(e.target.value)} autocomplete="off" defaultValue={student.categoriaId} max="100" type="text" id="categoriaId" className="form-control" style={{textAlign:'left'}}/>
          </div>
         
       </div>
       
          <div style={{textAlign: 'center',margin:"20px"}}>
            <Button
              color="danger " 
              onClick={() => cerrarModalActualizar()}
            >
              Cancelar
            </Button>
            <Button style={{textAlign: 'center',margin:"20px"}}
              color="success " 
              onClick={() => handleUpdate2(student.id)}
            >
              Editar
            </Button>
          </div>
       </div>

        </Modal>):null}
      </React.Fragment>
    );
}
 
export default BookList;

