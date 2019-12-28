import React, { useState, useEffect } from "react";
import { Wrapper, Header, Container, Footer } from "./styles/CustomStyles";
import grumpy from './images/grumpy.png';
import "./styles/Home.css";
import "./styles/index.css";
import tgLogo from './images/tg.png';


export default function Home(props) {
    return (
        <Wrapper>
            <Header>
              <p style={{color: "#ff8800", fontSize: "60px", fontFamily: "bold" }}>MEME++</p>
              <p style={{fontSize: "20px", fontFamily: "Palatino, serif", fontWeight:"3"}}>The place for sharing memes</p>
            </Header>
            <Container>
              <div>
                  <h1>Hello, username!</h1>
                  <p>You have been logged in</p>
              </div>
              <img src={grumpy}  alt={"grumpy"} style={{height:"400px", marginTop:"10px" }}/>   
            </Container>
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