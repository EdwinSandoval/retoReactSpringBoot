import React, {useState} from 'react';

import Home from './Components/Home'


function App() {

  const[conectado,setConectado]=useState(false)

  const acceder=(estado)=>{
    setConectado(estado)
  }

  return (
   
     <Home></Home>
   
  );
}

export default App;
