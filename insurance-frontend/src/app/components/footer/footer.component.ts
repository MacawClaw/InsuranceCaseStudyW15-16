import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  goToGithub(){
    window.open('https://github.com/MacawClaw/InsuranceCaseStudyW15-16', "_blank");
  }
}
