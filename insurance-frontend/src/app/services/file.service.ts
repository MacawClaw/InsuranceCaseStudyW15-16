import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = 'http://localhost:8081/files';

  constructor(private http: HttpClient) { }

  upload(file: File, claimId: number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('claimId', claimId.toString());

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  deleteFile(id: string): Observable<any> {
    /*
    const formData: FormData = new FormData();

    formData.append('id', fileId);
    
    const req = new HttpRequest('DELETE', `${this.baseUrl}/${fileId}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    //const url = `${this.baseUrl}/${fileId}`;
    //console.log(url);
    const fileMessage =  this.http.request(req);
    console.log(fileMessage);
    return fileMessage;
    */

    const url = `${this.baseUrl}/${id}`;

    return this.http.delete(url);


  }
}
