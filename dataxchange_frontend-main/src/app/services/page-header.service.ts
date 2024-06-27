import { Injectable } from '@angular/core';
import { PageHeaderComponent } from '../page-header/page-header.component';
import { Portal } from '@angular/cdk/portal';
import { DynamicDomainFormComponent } from '../components/dynamic-domain-form/dynamic-domain-form.component';

@Injectable({
  providedIn: 'root'
})
export class PageHeaderService {

  pageHeader: PageHeaderComponent | undefined;
  domainFormComponent: any | undefined;
  component_name: string | undefined;

  constructor() { }

  setFormActionsComponent(portal: Portal<any>, domainFormComponent: any, component_name: string): void {
    this.pageHeader?.attachActions(portal);
    this.domainFormComponent = domainFormComponent;
    this.component_name = component_name;
  }

  destroyFormActionComponent(){
    this.pageHeader?.removeActions();
  }
}
