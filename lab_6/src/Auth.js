import React, { useState, useEffect } from "react";
import "./styles/Auth.css";
import "./styles/index.css";
import { Link } from "react-router-dom";
import grumpy from './images/grumpy.png'
import tg from './images/tg.png'
import { 
  Wrapper, 
  Label, 
  Header, 
  ForSignup, 
  Footer, 
  ButtonSubmit, 
  Input } from "./styles/CustomStyles"


  export default function Login(props) {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");

    useEffect( () => { document.title = "Auth at MEME++" }, [] );
  
    function handleSubmit(event) {
      event.preventDefault();
      alert('You are trying to sign in with\n' +
      'login: ' + login + '\npassword: ' + password);
    }
  
    return (
      <Wrapper>
          <Header style={{marginTop: "30px"}}>
            <img src={grumpy}  alt={"Logo Cat"} style={{height: "100%"}}/>
            <p style={{color: "#f36f34", fontSize: "60px", fontFamily: "bold"}}>MEME++</p>
            <p style={{fontSize: "22px", fontFamily: "Palatino, serif", fontWeight:"3"}}>The place for sharing memes</p>
          </Header>
        <form className="SmallForm" onSubmit={handleSubmit}>
          <Label>Login</Label>
          <Input 
            type="login"
            value={login}
            onChange={e => setLogin(e.target.value)} 
          />
          <Label>Password</Label>
          <Input
            value={password}
            onChange={e => setPassword(e.target.value)}
            type="password"
          />
        <ButtonSubmit>
          Sign in
        </ButtonSubmit>
        </form>
        <ForSignup>
            <a>Dont have an account yet? </a>
            <Link to="/signup">
            <a href="/register">Register now</a>
            </Link>
        </ForSignup>
        <Footer>
            © 2019 MEME++
            <br/>
            <a href="https://t.me/telekazmin">
              <img src={tg} alt="TG Logo" style={{width: "40px", marginTop: "5px"}}></img>
            </a>
        </Footer>
      </Wrapper>
    );
  }