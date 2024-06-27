import { Component, OnDestroy, OnInit } from '@angular/core';
import { PageHeaderService } from '../../services/page-header.service';
import { ComponentPortal } from '@angular/cdk/portal';
import { FormActionsComponent } from '../../components/common/form-actions/form-actions.component';
import { AppService } from '../../app.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { RequestActionDialogComponent } from '../../components/common/request-action-dialog/request-action-dialog.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ApplicationReference, ValidationNotes } from '../../models/models';

@Component({
  selector: 'app-view-request',
  templateUrl: './view-request.component.html',
  styleUrl: './view-request.component.scss'
})
export class ViewRequestComponent implements OnInit, OnDestroy {

  public componetViewType: string | null = 'review';
  private requestId: string = '';

  public expandAllDetails: boolean = true;
  public viewRequesterData: any = {};

  public notes: ValidationNotes[] = [];
  enableAddNotes: boolean = false;
  selectedNote: ValidationNotes = {
    notesId: 0,
    notes: '',
    ingestionRequest: this.viewRequesterData
  };

  constructor(
    private pageHeaderService: PageHeaderService,
    private appService: AppService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private formBuilder: FormBuilder
  ) {
    const portal = new ComponentPortal(FormActionsComponent);
    pageHeaderService.setFormActionsComponent(portal, this, 'ViewRequestComponent');
  }
  ngOnDestroy(): void {
    this.pageHeaderService.destroyFormActionComponent();
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.requestId = params.get('requestId') ?? '';
      this.componetViewType = params.get('viewMode');
      this.appService.getRequestById(Number(this.requestId)).subscribe(res => {
        this.viewRequesterData = res;
        this.notes = res.notes;
      });
    });
  }

  // Header Actions
  cancel(): void {
    this.router.navigate(['manage-request']);
  }

  edit(): void {
    this.router.navigate(['ddf-request-form', this.requestId])
  }

  sendToDataXchange(): void {
    const form: FormGroup = this.formBuilder.group({
      comment: new FormControl('', Validators.required),
      notify: new FormControl(true)
    });
    this.openDialog(form, [], { title: "Send to Data Xchange", type: "submit" });
  }

  reject(): void {
    const options: any[] = [];
    this.appService.fetchReferenceData().subscribe(
      (res: ApplicationReference[]) => {
        res.forEach(data => {
          if (data.referenceDataType === "rejection_reason") {
            options.push(data.referenceData);
          }
        })
      },
      (error) => {
        console.error("Error fetching data", error);
      }
    );
    const form: FormGroup = this.formBuilder.group({
      reasonForRejection: new FormControl(''),
      url: new FormControl('', Validators.required),
      comment: new FormControl('', Validators.required),
      notify: new FormControl(true)
    });
    this.openDialog(form, options, { title: "Reject Request", type: "reject" });

  }

  openDialog(form: FormGroup, options: any[], dialogInfo: any): void {
    const dialogRef = this.dialog.open(RequestActionDialogComponent, {
      data: { form: form, options: options, dialogInfo: dialogInfo, ingestionRequestId: Number(this.requestId) }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.viewRequesterData = result;
      }
    });
  }

  moveToBacklog(): void {
    this.router.navigate(['manage-request']);
  }

  addNotes(): void {
    if (this.enableAddNotes && this.selectedNote.notesId === 0) {
    } else {
      this.enableAddNotes = true;
      this.selectedNote = {
        notesId: 0,
        notes: '',
        ingestionRequest: this.viewRequesterData
      };
    }
  }


  saveCurrentNote(): void {
    const _payload: any = {
      notesId: this.selectedNote?.notesId,
      notes: this.selectedNote.notes,
      request: this.viewRequesterData
    };
    if (this.selectedNote?.notesId) {
      this.appService.updateNotesById(_payload.notesId, _payload.request.ingestionRequestId, { notes: this.selectedNote.notes }).subscribe(res => {
        const index = this.notes.findIndex(note => note.notesId === this.selectedNote.notesId);
        if (index !== -1) {
          this.notes[index] = res;
        } else {
          this.notes.push(res);
        }
        this.resetAndCloseNoteEdit();
      });
    } else {
      this.appService.saveNotesByRequestIdId(_payload.request.ingestionRequestId, { notes: this.selectedNote.notes }).subscribe(res => {
        this.notes.push(res);
        this.resetAndCloseNoteEdit();
      });
    }
  }

  editNote(note: ValidationNotes, index: number): void {
    this.selectedNote = { ...note };
    this.enableAddNotes = true;
  }

  deleteNote(notesId: number, index: number): void {
    this.appService.deleteNotesById(notesId, this.viewRequesterData.ingestionRequestId).subscribe(() => {
      this.notes.splice(index, 1);
    });
  }

  closeEdit(): void {
    this.resetAndCloseNoteEdit();
  }

  resetAndCloseNoteEdit(): void {
    this.selectedNote = {
      notesId: 0,
      notes: '',
      ingestionRequest: this.viewRequesterData
    };
    this.enableAddNotes = false;
  }
}
