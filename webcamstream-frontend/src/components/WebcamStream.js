import React, { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';

function WebcamStream() {
    const [imageSrc, setImageSrc] = useState('');

    useEffect(() => {
        const stompClient = new Client({
            brokerURL: 'ws://localhost:8080/webcam-stream', // WebSocket URL
            connectHeaders: {},
            onConnect: () => {
                stompClient.subscribe('/topic/webcam-image', (message) => {
                    const byteArray = new Uint8Array(message.binaryBody.length);
                    for (let i = 0; i < message.binaryBody.length; i++) {
                        byteArray[i] = message.binaryBody[i];
                    }
                    const image = 'data:image/png;base64,' + btoa(String.fromCharCode(...byteArray));
                    setImageSrc(image);
                });
            },
        });

        stompClient.activate();

        return () => {
            stompClient.deactivate();
        };
    }, []);

    return <img src={imageSrc} alt="Webcam Stream" />;

}
export default WebcamStream;