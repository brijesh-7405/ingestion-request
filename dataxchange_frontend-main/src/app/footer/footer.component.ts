import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  disclaimer_text = "COMPLIANCE: Data & Analytics Marketplace is not a regulated/GxP compliant tool. To ensure compliance, you should directly reference the most recent, original source for results obtained with this tool."

  constructor() {
  }

  ngOnInit(): void {
  }

  getCurrentYear(): string {
    return new Date().getFullYear().toString();
  }

}
