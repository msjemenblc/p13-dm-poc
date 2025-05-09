import { Component } from '@angular/core';
import { ChatComponent } from './shared/components/chat/chat.component';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [ChatComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {}
