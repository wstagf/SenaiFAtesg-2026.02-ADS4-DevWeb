import React from "react"

function Senai() {
  const nome = "Turma Web Senai"
  let [aula, setContador] = React.useState('react')
  const [numero1, setNumero1] = React.useState(6)
  const [numero2, setNumero2] = React.useState(7)
  const [resultado, setResultado] = React.useState(0)


  const alterarAula = () => {
    console.log(aula)
    aula = "10";
    console.log(aula)
  }
  return (
    <>
     <p>Olá {nome}</p>
     <p>Contador: {aula}</p>
     <button onClick={() => setContador(aula + 1)}>
       Incrementar
     </button>

     <button onClick={alterarAula}>
       Alterar Aula
     </button>

    <div>
        <label>Número 1</label>
        <input id="numero1" type="number" value={numero1}
         onChange={(e) => setNumero1(Number(e.target.value))} />
        <br/>
        <label>Número 2</label>
        <input id="numero2" type="number" value={numero2} 
          onChange={(e) => setNumero2(Number(e.target.value))}/>
        <br/> 
        <button onClick={() => setResultado(numero1 + numero2)}>+</button>
        <button onClick={() => setResultado(numero1 - numero2)}>-</button>
        <button onClick={() => setResultado(numero1 * numero2)}>*</button>
        <button onClick={() => setResultado(numero1 / numero2)}>/</button>
   
      <p>Resultado: <span id="destino">{resultado}</span></p>
    </div>
    
    </>
  )
}

export default Senai
