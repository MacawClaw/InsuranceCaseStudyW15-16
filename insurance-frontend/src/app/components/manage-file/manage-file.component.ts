import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, map } from 'rxjs';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-manage-file',
  templateUrl: './manage-file.component.html',
  styleUrls: ['./manage-file.component.css']
})
export class ManageFileComponent {
  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  claimId!: any;

  fileInfos?: Observable<any>;

  constructor(private fileService: FileService, private route: ActivatedRoute) { }

  ngOnInit() {
  this.route.params.subscribe(params => {
    this.claimId = params['claimId'];
    // Retrieve the claimId parameter from the URL
  });
  this.fileInfos = this.fileService.getFiles();
  this.fileInfos = this.fileInfos.pipe(
    map(files => files.filter((file: { claimId: any; }) => file.claimId == this.claimId))
  );
  
  console.log(this.fileInfos);
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
  this.progress = 0;

  if (this.selectedFiles) {
    const file: File | null = this.selectedFiles.item(0);

    if (file) {
      this.currentFile = file;

      this.fileService.upload(this.currentFile, this.claimId).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round(100 * event.loaded / event.total);
          } else if (event instanceof HttpResponse) {
            this.message = event.body.message;
            this.fileInfos = this.fileService.getFiles();
            this.fileInfos = this.fileInfos.pipe(
              map(files => files.filter((file: { claimId: any; }) => file.claimId == this.claimId))
            );
          }
        },
        (err: any) => {
          console.log(err);
          this.progress = 0;

          if (err.error && err.error.message) {
            this.message = err.error.message;
          } else {
            this.message = 'Could not upload the file!';
          }

          this.currentFile = undefined;
        });
    }

    this.selectedFiles = undefined;
  }
  }

  /*
  deleteFile(fileId:any) {
    console.log(fileId);
    const returnMessage = this.fileService.deleteFile(fileId);
    alert(returnMessage);
  }
  */

  deleteFile(fileId: string): void {
    this.fileService.deleteFile(fileId).subscribe(() =>
      {this.fileInfos = this.fileService.getFiles();
      this.fileInfos = this.fileInfos.pipe(
        map(files => files.filter((file: { claimId: any; }) => file.claimId == this.claimId)));
      })
  }

}
