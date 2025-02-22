import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import React, {Fragment, useState, useEffect} from 'react';
import BookList from './ListaProductos'
import Form from './Form'
// import Cursos from "../Components-cursos/Cursos";
import axios from "axios"

export default function Home(params) {

    const [book, setBook] = useState({
       
        nombre:'',
        precio:0.0,
        descripcion:'',
        marca:'',
        categoria:''
      })
      
    
      const [books, setBooks] = useState([])
    
      const [curso,setcurso]=useState(true)
      const [listUpdated, setListUpdated] = useState(false)
      const [buscar,setBuscar]=React.useState("")
    
      useEffect(() => {
        const getBooks = () => {
          fetch('http://localhost:8081/producto/all')
          .then(res => res.json())
          .then(res => setBooks(res))
        }
        // console.log(getBooks());
        
        getBooks()
        setListUpdated(false)
      }, [listUpdated])

      const llamarCursos=()=>{
          setcurso(false)
      }
      const llamarHome=()=>{
        setcurso(true)
     }


    const buscarEstudiante = async (e) => {//elimina
      e.preventDefault();
      const respuesta=await axios.post('http://localhost:9000/api/like/estudiante',
      {
        buscar  
      })

      console.log('userssssssssss',respuesta.data);
      setBooks(respuesta.data)
      
    }

  return (
    
    <Router>
    <Fragment>
        <nav className="navbar navbar-dark bg-dark">
        <button  style={{background:'transparent',border:"none",marginTop:'-10px',paddingLeft:'100px',marginTop:0,fontSize:"20px",color:"white"}} onClick={()=>llamarHome()}><Link to={"/home"} className="nav-link">PRODUCTOS</Link></button>
      <button  style={{background:'transparent',border:"none",marginTop:'-10px',paddingLeft:'100px',marginTop:0,fontSize:"20px",color:"white"}} onClick={()=>llamarCursos()}><Link to={"/cursos"} className="nav-link">CATEGORIA</Link></button>

      <a className="navbar-brand" href="#" style={{paddingLeft:'25px'}}>ABOUT</a>
      <a className="navbar-brand" href="./Login" style={{paddingRight:'100px'}}>CERRAR SESION</a>
       </nav>

      {curso?(<div>
          <div className="col-5 row justify-content-center mx-auto" style={{textAlign:'left',marginTop:'20px'}}>
                <h2 style={{textAlign: 'center'}}>Nuevo Producto</h2>
                <Form style={{textAlign:'right'}} book={book} setBook={setBook} setListUpdated={setListUpdated}/>
          </div>
          <div className="container">
              
            <div className="row row_ancho">
              <div className="col-12 ">
                <h2 style={{textAlign: 'center'}}>Lista de Productos</h2>
                <form className="form-inline md-form mr-auto mb-4" style={{textAlign:'right'}}>
                  <input className="form-control mr-sm-2" type="text" onChange={(e)=>setBuscar(e.target.value)} placeholder="Search" aria-label="Search"/>
                  <button className=" btn btn-outline-info" type="button"  onClick={(e)=>buscarEstudiante(e)} >Buscar</button>
                  <div className="col-3 " style={{textAlign:'right',paddingLeft:'750px'}}>

                  {/* <button style={{textAlign:'right'}} onClick={(e)=>borrarTodos(e)} type="button" className="btn  btn-outline-danger"  >Borrar Todo</button> */}
                  </div>
                  
                </form>

                <BookList book={book} setBook={setBook} books={books} setListUpdated={setListUpdated} setBooks={setBooks}/>
              </div>
              
            </div>
          </div>
      </div>):
        <Switch>
            <Route exact path={["/", "/cursos"]}  />
        </Switch>}
    </Fragment>
    </Router>
    
  )
}


    