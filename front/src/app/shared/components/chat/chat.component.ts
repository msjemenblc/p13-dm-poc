import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Message } from '../../models/message.model';
import { MessageService } from '../../../core/services/message/message.service';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor } from '@angular/common';

@Component({
    selector: 'app-chat',
    standalone: true,
    templateUrl: './chat.component.html',
    styleUrl: './chat.component.scss',
    imports: [NgFor, FormsModule, CommonModule],
})
export class ChatComponent implements OnInit {
    @ViewChild('chatContainer') chatContainer!: ElementRef;

    messages: Message[] = [];
    senderId: number = 1;
    receiverId: number = 2;
    content: string = '';

    constructor(private messageService: MessageService) {}

    ngOnInit(): void {
        this.loadMessages();
    }

    private loadMessages(): void {
        this.messageService.getMessages().subscribe((data) => {
            this.messages = data;
            setTimeout(() => this.scrollToBottom(), 0);
        });
    }

    public sendMessage(): void {
        if (this.content.trim()) {
            this.messageService.sendMessage(this.senderId, this.receiverId, this.content).subscribe((message) => {
                this.messages.push(message);
                this.content = '';
                setTimeout(() => this.scrollToBottom(), 0);
            })
        }
    }

    public toggleUser(): void {
        [this.senderId, this.receiverId] = [this.receiverId, this.senderId];
    }

    private scrollToBottom(): void {
        if (this.chatContainer && this.chatContainer.nativeElement) {
            console.log("Scrolling to bottom...");
            this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
        } else {
            console.error("chatContainer non trouvé !");
        }
    }
}
