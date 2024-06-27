import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Input,
  OnChanges,
  OnDestroy,
  QueryList,
  SimpleChanges,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CREATE_INGESTION_CONTROLS } from '../../dynamic-form-controls/create-ingestion-controls';
import { PageHeaderService } from '../../services/page-header.service';
import { ComponentPortal } from '@angular/cdk/portal';
import { MatStepHeader, MatStepper } from '@angular/material/stepper';
import { formsValidators } from './validators';
import { DynamicFormComponent } from '../dynamic-form/dynamic-form.component';
import { ApplicationReference, CreateDomain, IngestionRequest } from '../../models/models';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { RequestConfirmationDialogComponent } from '../common/request-confirmation-dialog/request-confirmation-dialog.component';
import { FormActionsComponent } from '../common/form-actions/form-actions.component';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-dynamic-domain-form',
  templateUrl: './dynamic-domain-form.component.html',
  styleUrl: './dynamic-domain-form.component.scss',
})
export class DynamicDomainFormComponent implements AfterViewInit, OnDestroy, OnChanges {

  @Input() editData: any = null;
  @ViewChild('stepper') stepper!: MatStepper;
  @ViewChildren(DynamicFormComponent)
  dynamicFormComponents!: QueryList<DynamicFormComponent>;

  ingestionRequest: IngestionRequest = {} as IngestionRequest;


  createDomainData: CreateDomain[] = JSON.parse(
    JSON.stringify(CREATE_INGESTION_CONTROLS)
  );


  forms: FormGroup[] = this.createDomainData.map(() =>
    this.formBuilder.group({})
  );

  get currentStep(): number {
    if (!this.afterViewInited) {
      return 0;
    }

    return this.stepper.selectedIndex;
  }

  get totalSteps(): number {
    if (!this.afterViewInited) {
      return 0;
    }

    return this.stepper.steps.length;
  }

  private afterViewInited = false;
  private subscriptions: (Subscription | undefined)[] = [];

  constructor(
    private pageHeaderService: PageHeaderService,
    private formBuilder: FormBuilder,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private dialog: MatDialog,
    private appService: AppService
  ) {
    this.reInitializeHeader();
  }

  applicationReference: ApplicationReference[] = [];

  ngOnInit(): void {
    this.appService.fetchReferenceData().subscribe(
      (res: ApplicationReference[]) => {
        this.applicationReference = res;
        this.applicationReference.forEach(data => this.updateDomainData(data))
      },
      (error) => {
        console.error("Error fetching data", error);
      }
    );
  }

  updateDomainData(applicationReference: ApplicationReference) {
    this.createDomainData.forEach(domain => {
      domain.data.forEach(field => {
        if (field.reference_data_type && field.reference_data_type === applicationReference.referenceDataType) {
          if (applicationReference.referenceData == "As per Contract") {
            field.data?.push({
              name: applicationReference.referenceData,
              value: {
                value: applicationReference.referenceData,
                date: null
              }
            });
          } else {
            field.data?.push({
              name: applicationReference.referenceData,
              value: applicationReference.referenceData
            });
          }
        }
      });
    });
  }

  reInitializeHeader() {
    const portal = new ComponentPortal(FormActionsComponent);
    this.pageHeaderService.setFormActionsComponent(portal, this, 'DynamicDomainFormComponent');
  }

  ngAfterViewInit(): void {
    this.afterViewInited = true;

    this.initView();
    this.initStepHeaders();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((sub) => sub && sub.unsubscribe());
    this.pageHeaderService.destroyFormActionComponent()
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.editData = changes['editData'].currentValue;
    this.reInitializeHeader();
    if (this.editData) {
      const formData = this.transformEditDataToFormData(this.editData);
      formData.forEach((val: any, i: number) => {
        this.forms[i].patchValue(val);
      })
    }
  }

  private transformEditDataToFormData(editData: any): any[] {
    // Transform editData into the form data array format expected by the forms
    let retentionRules: any = editData.retentionRules;
    let retentionRulesContractDate = editData.retentionRulesContractDate;
  
    if (editData.retentionRules === "As per Contract") {
      retentionRules = {
        value: "As per Contract",
        date: retentionRulesContractDate
      };
    }
    return [
      {
        requesterName: editData.requesterName,
        requesterMudid: editData.requesterMudid,
        requesterEmail: editData.requesterEmail,
        datasetName: editData.datasetName,
        datasetSmeName: editData.datasetSmeName,
        datasetSmeMudid: editData.datasetSmeMudid,
        datasetSmeEmail: editData.datasetSmeEmail,
        requestRationaleReason: editData.requestRationaleReason,
        datasetOriginSource: editData.datasetOriginSource,
        currentDataLocationRef: editData.currentDataLocationRef,
        dataLocationPath: editData.dataLocationPath,
        meteorSpaceDominoUsageFlag: editData.meteorSpaceDominoUsageFlag ? "Yes" : "No",
        ihdFlag: editData.ihdFlag ? "Yes" : "No",
        datasetRequiredForRef: editData.datasetRequiredForRef
      }, {
        estimatedDataVolumeRef: editData.estimatedDataVolumeRef,
        dataRefreshFrequency: editData.dataRefreshFrequency,
        analysisInitDt: editData.analysisInitDt,
        analysisEndDt: editData.analysisEndDt,
        dtaContractCompleteFlag: editData.dtaContractCompleteFlag ? "Yes" : "No",
        dtaExpectedCompletionDate: editData.dtaExpectedCompletionDate
      }, {
        dataCategoryRefs: editData.dataCategoryRefs,
        datasetTypeRef: editData.datasetTypeRef,
        studyIds: editData.studyIds,
        datasetOwnerName: editData.datasetOwnerName,
        datasetOwnerMudid: editData.datasetOwnerMudid,
        datasetOwnerEmail: editData.datasetOwnerEmail,
        datasetStewardName: editData.datasetStewardName,
        datasetStewardMudid: editData.datasetStewardMudid,
        datasetStewardEmail: editData.datasetStewardEmail,
        contractPartner: editData.contractPartner,
        retentionRules: retentionRules,
        usageRestrictions: editData.usageRestrictions,
        userRestrictions: editData.userRestrictions,
        informationClassificationTypeRef: editData.informationClassificationTypeRef,
        piiTypeRef: editData.piiTypeRef,
        therapyAreas: editData.therapyAreas,
        techniqueAndAssays: editData.techniqueAndAssays,
        indications: editData.indications,
      }, {
        targetIngestionStartDate: editData.targetIngestionStartDate,
        targetIngestionEndDate: editData.targetIngestionEndDate,
        targetPath: editData.targetPath,
        datasetTypeIngestionRef: editData.datasetTypeIngestionRef,
        guestUsersEmail: editData.guestUsersEmail,
        whitelistIpAddresses: editData.whitelistIpAddresses,
        externalStagingContainerName: editData.externalStagingContainerName,
        domainRequestId: editData.domainRequestId,
        externalDataSourceLocation: editData.externalDataSourceLocation,
        gskAccessSourceLocationRef: editData.gskAccessSourceLocationRef,
        externalSourceSecretKeyName: editData.externalSourceSecretKeyName,
      }
    ];
  }

  private initStepHeaders(): void {
    this.stepper._stepHeader.forEach((header: MatStepHeader, i: number) => {
      const headerEl = header._elementRef.nativeElement;
      ['mousedown', 'touchstart', 'keydown'].forEach((eventName: string) => {
        headerEl.addEventListener(eventName, (event) => {
          if (!this.validate()) {
            event.stopPropagation();
            event.preventDefault();
          }
        })
      })
    });
  }

  private initView(): void {
    /// Prioritization details

    const resetPrioritizationDetailsForm = () => {
      this.hideControl('dtaExpectedCompletionDate');
    };

    resetPrioritizationDetailsForm();
    this.subscriptions.push(
      this.forms[1]
        .get('dtaContractCompleteFlag')
        ?.valueChanges.subscribe((value) => {
          resetPrioritizationDetailsForm();

          value === "No"
            ? this.showControl('dtaExpectedCompletionDate')
            : this.hideControl('dtaExpectedCompletionDate');
        })
    );


    /// Dataset details

    const resetDatasetDetailsForm = () => {
      this.hideControl('studyIds');
      this.hideControl('datasetOwnerName');
      this.hideControl('datasetStewardName');
      this.hideControl('contractPartner');
      this.hideControl('retentionRules');
      this.hideControl('usageRestrictions');
      this.hideControl('userRestrictions');
      this.hideControl('informationClassificationTypeRef');
      this.hideControl('piiTypeRef');
      this.hideControl('therapyAreas');
      this.hideControl('techniqueAndAssays');
      this.hideControl('indications');
    };

    resetDatasetDetailsForm();

    const reMapDataSetTypes = (value: string) => {
      resetDatasetDetailsForm();
      if (
        value === "Type 1 - Internal" &&
        this.getControlValue('datasetRequiredForRef') ===
        'Industrialization'
      ) {
        this.showControl('studyIds');
      } else if (
        (value === "Type 2 – Contract (pre-existing licensed data)" || value == "Type 3 – Contract (new data)" || value == "Type 4 - Public") &&
        this.getControlValue('datasetRequiredForRef') ===
        'Industrialization'
      ) {
        this.showControl('therapyAreas');
        this.showControl('techniqueAndAssays');
        this.showControl('indications');
      } else if (value === "Type 1 - Internal") {
        this.showControl('studyIds');
        this.showControl('datasetOwnerName');
        this.showControl('datasetStewardName');
      } else if (value === "Type 2 – Contract (pre-existing licensed data)" || value === "Type 3 – Contract (new data)" || value === "Type 4 - Public") {
        this.showControl('datasetOwnerName');
        this.showControl('datasetStewardName');
        this.showControl('contractPartner');
        this.showControl('retentionRules');
        this.showControl('usageRestrictions');
        this.showControl('userRestrictions');
        this.showControl('informationClassificationTypeRef');
        this.showControl('piiTypeRef');
      }

    }

    this.subscriptions.push(
      this.forms[0].get('datasetRequiredForRef')?.valueChanges.subscribe((value) => {
        reMapDataSetTypes(this.forms[2].get('datasetTypeRef')?.value)
      })
    )
    this.subscriptions.push(
      this.forms[2].get('datasetTypeRef')?.valueChanges.subscribe((value) => { reMapDataSetTypes(value) })
    );


    /// Technical details
    const resetTechnicalDetailsForm = () => {
      this.hideControl('guestUsersEmail');
      this.hideControl('whitelistIpAddresses');
      this.hideControl('externalStagingContainerName');
      this.hideControl('externalDataSourceLocation');
      this.hideControl('gskAccessSourceLocationRef');
      this.hideControl('externalSourceSecretKeyName');
    };

    resetTechnicalDetailsForm();
    this.subscriptions.push(
      this.forms[3]
        .get('datasetTypeIngestionRef')
        ?.valueChanges.subscribe((value) => {
          resetTechnicalDetailsForm();
          if (value === "Third party will push data into XYZ") {
            this.showControl('guestUsersEmail');
            this.showControl('whitelistIpAddresses');
            this.showControl('externalStagingContainerName');
          } else if (value === "XYZ will push data") {
            this.showControl('externalDataSourceLocation');
            this.showControl('gskAccessSourceLocationRef');
            this.showControl('externalSourceSecretKeyName');
          }
        })
    );

    this.cdr.detectChanges();
  }


  // Set form control visible
  showControl(key: string) {
    const dynamicFormComponent = this.dynamicFormComponents.find(
      (dynamicFormComponent) =>
        dynamicFormComponent.formControlComponents.has(key)
    );
    dynamicFormComponent?.showControl(key);
  }

  // Set form control invisible
  hideControl(key: string) {
    const dynamicFormComponent = this.dynamicFormComponents.find(
      (dynamicFormComponent) =>
        dynamicFormComponent.formControlComponents.has(key)
    );
    dynamicFormComponent?.hideControl(key);
  }

  // Get form control's value
  getControlValue(key: string): any {
    const dynamicFormComponent = this.dynamicFormComponents.find(
      (dynamicFormComponent) =>
        dynamicFormComponent.formControlComponents.has(key)
    );
    return dynamicFormComponent?.form.get(key)?.value;
  }

  // Go to previous step
  previous(): void {
    this.stepper.previous();
  }

  // Go to next step
  next(): void {
    if (!this.validate()) {
      return;
    }

    this.stepper.next();
  }

  buildPayload(forms: FormGroup[]): IngestionRequest {
    const retentionRulesControl = forms[2].get('retentionRules')?.value;

    let retentionRules = retentionRulesControl;
    let retentionRulesContractDate = "";
  
    if (typeof retentionRulesControl === 'object' && retentionRulesControl?.value === "As per Contract") {
      retentionRules = retentionRulesControl.value;
      retentionRulesContractDate = retentionRulesControl?.date || "";
    }
    return {
      requesterName: forms[0].get('requesterName')?.value,
      requesterMudid: "requesterMudid", // mock value for requesterMudid
      requesterEmail: "requesterEmail@gmail.com",  // mock value for requesterEmail
      datasetName: forms[0].get('datasetName')?.value,
      datasetSmeName: forms[0].get('datasetSmeName')?.value,
      datasetSmeMudid: "datasetSmeMudid",  // mock value for datasetSmeMudid
      datasetSmeEmail: "datasetSmeEmail@gmail.com", // mock value of datasetSmeEmail
      requestRationaleReason: forms[0].get('requestRationaleReason')?.value,
      datasetOriginSource: forms[0].get('datasetOriginSource')?.value,
      currentDataLocationRef: forms[0].get('currentDataLocationRef')?.value,
      dataLocationPath: forms[0].get('dataLocationPath')?.value,
      meteorSpaceDominoUsageFlag: forms[0].get('meteorSpaceDominoUsageFlag')?.value === "Yes" ? true : false,
      ihdFlag: forms[0].get('ihdFlag')?.value === "Yes" ? true : false,
      datasetRequiredForRef: forms[0].get('datasetRequiredForRef')?.value,
      estimatedDataVolumeRef: forms[1].get('estimatedDataVolumeRef')?.value,
      dataRefreshFrequency: forms[1].get('dataRefreshFrequency')?.value,
      analysisInitDt: forms[1].get('analysisInitDt')?.value,
      analysisEndDt: forms[1].get('analysisEndDt')?.value,
      dtaContractCompleteFlag: forms[1].get('dtaContractCompleteFlag')?.value === "Yes" ? true : false,
      dtaExpectedCompletionDate: forms[1].get('dtaExpectedCompletionDate')?.value,
      dataCategoryRefs: forms[2].get('dataCategoryRefs')?.value,
      datasetTypeRef: forms[2].get('datasetTypeRef')?.value,
      studyIds: forms[2].get('studyIds')?.value,
      datasetOwnerName: forms[2].get('datasetOwnerName')?.value,
      datasetOwnerMudid: "datasetOwnerMudid", // mock value for datasetOwnerMudid
      datasetOwnerEmail: "datasetOwnerEmail@gmail.com", // mock value for datasetOwnerEmail
      datasetStewardName: forms[2].get('datasetStewardName')?.value,
      datasetStewardMudid: "datasetStewardMudid", // mock value for datasetStewardMudid
      datasetStewardEmail: "datasetStewardEmail@gmail.com",  // mock value for datasetStewardEmail
      contractPartner: forms[2].get('contractPartner')?.value,
      retentionRules,
      retentionRulesContractDate,
      usageRestrictions: forms[2].get('usageRestrictions')?.value,
      userRestrictions: forms[2].get('userRestrictions')?.value,
      informationClassificationTypeRef: forms[2].get('informationClassificationTypeRef')?.value,
      piiTypeRef: forms[2].get('piiTypeRef')?.value,
      therapyAreas: forms[2].get('therapyAreas')?.value,
      techniqueAndAssays: forms[2].get('techniqueAndAssays')?.value,
      indications: forms[2].get('indications')?.value,
      targetIngestionStartDate: forms[3].get('targetIngestionStartDate')?.value,
      targetIngestionEndDate: forms[3].get('targetIngestionEndDate')?.value,
      targetPath: forms[3].get('targetPath')?.value,
      datasetTypeIngestionRef: forms[3].get('datasetTypeIngestionRef')?.value,
      guestUsersEmail: forms[3].get('guestUsersEmail')?.value.toString().split(',').map((email: string) => email.trim()),
      whitelistIpAddresses: forms[3].get('whitelistIpAddresses')?.value.toString().split(',').map((ip: string) => ip.trim()),
      externalStagingContainerName: forms[3].get('externalStagingContainerName')?.value,
      domainRequestId: forms[3].get('domainRequestId')?.value,
      externalDataSourceLocation: forms[3].get('externalDataSourceLocation')?.value,
      gskAccessSourceLocationRef: forms[3].get('gskAccessSourceLocationRef')?.value,
      externalSourceSecretKeyName: forms[3].get('externalSourceSecretKeyName')?.value
    };
  }


  ingestionIdBySaveClick: number = 0;

  
  submit(): void {
    if (!this.validate()) {
      return;
    }

    const payload = this.buildPayload(this.forms);

    if (this.ingestionIdBySaveClick === 0) {
      this.appService.saveRequest(payload, true).subscribe(res => {
        this.openDialog(res.ingestionRequestId);
      })
    } else {
      this.appService.updateRequest(this.ingestionIdBySaveClick, payload, true).subscribe(res => {
        this.openDialog(res.ingestionRequestId);
      })
    }
  }

  openDialog(requestId: String): void {
    const dialogRef = this.dialog.open(RequestConfirmationDialogComponent, {
      data: { requestFor: 'Medicines for Malaria Venture', requestId: requestId }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result == 'view')
        this.router.navigate(['fast-track-request', requestId, 'review'])
    });
  }

  save(): void {
    if (!this.validate()) {
      return;
    }
    const payload = this.buildPayload(this.forms);
    if (this.ingestionIdBySaveClick === 0) {
      this.appService.saveRequest(payload, false).subscribe(res => {
        this.ingestionIdBySaveClick = res.ingestionRequestId;
      });
    } else {
      this.appService.updateRequest(this.ingestionIdBySaveClick, payload, false).subscribe()
    }
  }

  submitOnEditPage() {
    if (!this.validate()) {
      return;
    }
    const payload = this.buildPayload(this.forms);
    this.appService.updateRequest(this.editData.ingestionRequestId, payload, true).subscribe(res => {
      this.router.navigate(['fast-track-request', this.editData.ingestionRequestId, 'view']);
    })
  }

  update() {
    if (!this.validate()) {
      return;
    }
    const payload = this.buildPayload(this.forms);
    this.appService.updateRequest(this.editData.ingestionRequestId, payload, false).subscribe(res => {
      this.router.navigate(['fast-track-request', this.editData.ingestionRequestId, 'view']);
    })
  }


  //TODO
  saveAndClose(): void {
    if (!this.validate()) {
      return;
    }

    const payload = this.buildPayload(this.forms);
    if (this.ingestionIdBySaveClick === 0) {
      this.appService.saveRequest(payload, false).subscribe();
    } else {
      this.appService.updateRequest(this.ingestionIdBySaveClick, payload, false).subscribe()
    }    this.router.navigate(['/'])
  }

  cancel(): void {

    if (this.editData && this.editData.requestId)
      this.router.navigate(['fast-track-request', this.editData.requestId, 'view']);
    else
      this.router.navigate(['/']);
  }

  // Validate the current form
  private validate(): boolean {
    const form = this.forms[this.currentStep];
    const formValidators = formsValidators[this.currentStep];

    const fieldNames = Object.keys(form.controls);

    fieldNames.forEach((fieldName) => {
      const validators = formValidators[fieldName];
      const control = form.controls[fieldName];

      if (validators) {
        control.setValidators(validators);
        control.updateValueAndValidity();
      }
    });

    return form.valid;
  }

  // Set the icons of the steps to green when the steps are finished
  updateStepHeaderColorAndText() {
    this.stepper._stepHeader.forEach((header: MatStepHeader, i: number) => {
      const headerIconEl =
        header._elementRef.nativeElement.querySelector<HTMLElement>(
          '.mat-step-icon'
        )!;
      const headerContentEl =
        header._elementRef.nativeElement.querySelector<HTMLElement>(
          '.mat-step-icon .mat-step-icon-content'
        )!;
      const lineEl =
        header._elementRef.nativeElement.parentElement!.querySelectorAll<HTMLElement>(
          '.mat-stepper-horizontal-line'
        )[i];

      headerContentEl.innerHTML = `${i + 1}`;
      if (this.forms[i].valid && (this.forms[i].touched || this.stepper.selectedIndex === i + 1)) {
        headerIconEl.style.backgroundColor = '#10a103';
        lineEl.style.borderColor = '#10a103';
      }
    });
  }
}
