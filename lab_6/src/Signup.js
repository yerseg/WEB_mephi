import React, { useState, useEffect } from "react";
import "./styles/Signup.css";
import grumpy from './images/grumpy.png'
import tgLogo from './images/tg.png'
import { 
    Wrapper, 
    Label, 
    Header, 
    ForSignup, 
    Footer, 
    ButtonSubmit, 
    Input,
    OrangeHeader,
    Greeting } from "./styles/CustomStyles"
import { Link } from "react-router-dom";


export default function Signup(props) {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");

    useEffect( () => { document.title = "Join MEME++" }, [] );
    let reg = /[\u0400-\u04FF]/;

    function handleSubmit(event) {
        event.preventDefault();
        let isNameCorrect = true;
        for (let char of name){
            if (! reg.test(char)) { isNameCorrect = false; }
        }
        let exception = "";
        exception += (!isNameCorrect ? 'Name must contain only cyrillic characters ' : '');
        exception += (password.length < 6 ? '\nPassword must contain 6+ symbols' : '');
        if (exception.length != 0) {
            alert(exception);
        }
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
                <a>Have an account already? </a>
                <Link to="/signin">
                <a href="#">Login now</a>
                </Link>
                </ForSignup>
            <Footer>
                © 2019 MEME++
                <br/>
                <a href="https://t.me/telekazmin">
                <img src={tgLogo} alt="TG Logo" style={{width: "40px", marginTop: "5px"}}></img>
                </a>
            </Footer>
        </Wrapper>
    );
}