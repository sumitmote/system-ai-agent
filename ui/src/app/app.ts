import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { marked } from 'marked';

type ChatRole = 'user' | 'assistant';

interface ChatMessage {
  role: ChatRole;
  text: string;
  html?: string;
}

interface ChatResponse {
  answer: string;
}

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Spring AI Chat');
  protected message = '';
  protected readonly loading = signal(false);
  protected readonly error = signal('');
  protected readonly conversation = signal<ChatMessage[]>([
    {
      role: 'assistant',
      text: 'Ask me anything about your knowledge base.',
      html: this.renderAssistantMessage('Ask me anything about your knowledge base.')
    }
  ]);

  constructor(private readonly http: HttpClient) {}

  protected async sendMessage(): Promise<void> {
    const userMessage = this.message.trim();
    if (!userMessage || this.loading()) {
      return;
    }

    this.error.set('');
    this.message = '';
    this.loading.set(true);
    this.conversation.update((messages) => [...messages, { role: 'user', text: userMessage }]);

    try {
      const response = await firstValueFrom(
        this.http.post<ChatResponse>('/api/chat', { message: userMessage })
      );
      this.conversation.update((messages) => [
        ...messages,
        {
          role: 'assistant',
          text: response.answer,
          html: this.renderAssistantMessage(response.answer)
        }
      ]);
    } catch {
      this.error.set('Unable to reach the chat API. Make sure Spring Boot is running.');
    } finally {
      this.loading.set(false);
    }
  }

  private renderAssistantMessage(markdown: string): string {
    return String(marked.parse(markdown, { gfm: true, breaks: true }));
  }
}
