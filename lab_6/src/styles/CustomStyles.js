import styled from 'styled-components'

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  height: 100%;
`;

export const Label = styled.p`
  font-family: sans-serif;
  font-weight: 550; 
  font-size: 16px; 
  margin-top: 15px;
  margin-bottom: 10px;
  color: #2e2e2e;
  padding-left: 20px;
`;

export const Header = styled.div`
  display: flex;
  flex-direction: column;
  flex: 0 0 auto;
  align-self: center;
  align-items: center;	
  justify-content: space-around;
  height: 80px;
  padding-top: 8px;
`; 

export const ForSignup = styled.div`
	margin-top: 20px;
	font-family: sans-serif;
	font-weight: 440; 
	font-size: 16px; 
	align-self: center;
`;

export const Footer = styled.div`
  display: flex;
  flex: 0 0 auto;
  flex-direction: column;
  align-self: center;
  align-items: center;	
  font-family: sans-serif;
`;

export const ButtonSubmit = styled.button`
  background-color: #1aaa55;
  color: white;
  border: 1px solid #168f48;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 500;
  width: 280px;
  height: 40px;
  margin-top: 20px;
  margin-bottom: 10px;
  align-self: center;
  &:hover {
    background-color: #158944;
  }
`;

export const Input = styled.input`
  width: 271px;
  padding-top: 5px;
  padding-bottom: 6px;
  padding-left: 5px;
  font-size: 15px;
  margin-left: 20px;
`;

export const Container = styled.div`
    display: flex;
    flex: 1 0 auto;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
`;

export const OrangeHeader = styled(Header)`
    display: flex;
    flex-direction: column;
    background-color: #fc9d72;
    color: black;
    justify-content: center;
`;

export const Greeting = styled.p`
    font-size: 22px; 
    font-family: sans-serif;
    font-weight: 7; 
    margin: 0px;
    padding: 15px;
`;