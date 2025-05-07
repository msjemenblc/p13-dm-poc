import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../../../shared/models/message.model';

@Injectable({
    providedIn: 'root'
})
export class MessageService {
    private apiUrl = 'http://localhost:8080/api/messages';

    constructor(private http: HttpClient) {}

    getMessages(): Observable<Message[]> {
        return this.http.get<Message[]>(this.apiUrl);
    }

    sendMessage(senderId: number, receiverId: number, content: string): Observable<Message> {
        const message = {
            senderId: senderId,
            receiverId: receiverId,
            content: content,
        };

        return this.http.post<Message>(this.apiUrl, message);
    }
}