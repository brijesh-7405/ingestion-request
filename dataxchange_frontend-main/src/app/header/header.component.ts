import { Component, ChangeDetectorRef } from '@angular/core';

const QUICK_LINKS = ['/', '/', '/', '/', '/', '/', '/', '/'];

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent{
  currentInfoMudId: any;
  urls = QUICK_LINKS;
  userName: any;
  constructor() {
  }

  imgHost = false ? "img/logo/" : "../../assets/"
  windowOrigin = window.location.origin;;
  homePage = window.location.origin + "/home/#/"
  oldNameSpace= '';

}
