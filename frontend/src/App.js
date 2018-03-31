import React, { Component } from 'react';
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      connected: false,
      rate: 0,
      total: 0,
    };
  }

  setConnected(connected) {
    this.setState({
      connected: true,
      rate: 0,
      total: 0,
    });
  }

  setStatus(rate, total) {
    this.setState({
      connected: true,
      rate: rate,
      total: total,
    });
  }

  componentDidMount() {
    const socket = new SockJS('http://localhost:8080/telemetry');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
        this.setConnected(true);
        stompClient.subscribe('/topic/status', response => {
          const status = JSON.parse(response.body);
          this.setStatus(status.rate, status.count);
        });
    });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <Status connected={ this.state.connected } rate={ this.state.rate } total={ this.state.total } />
      </div>
    );
  }
}

function Status(props) {
  const status = props.connected ? 'Connected' : 'Connecting...'
  return (
    <div>
      <p>{ status }</p>
      <p>{ props.rate } messages per second</p>
      <p>{ props.total } total messages</p>
    </div>
  );
}

export default App;
