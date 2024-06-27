import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { StandardValues, UserRoles } from "../models/models";
import { map } from 'rxjs/operators';
import { Location } from '@angular/common';
import {
  GET_STANDARD_VALUES,
  GET_USER_ROLES,
  CREATE_INGESTION_REQUEST
} from '../../constants/apiPath';

@Injectable({
  providedIn: "root",
})
export class UtillService {
  constructor(private http: HttpClient, private location: Location) { }
   
  haveAccess:boolean = false;
  isGlobalSearchEnabled = false;
  isuserfound: boolean = false;
  viewActiveDomains = true;

  
  //Get standard values or configurations API
  getStandardValues(mudId:any) {
    let headers = {
      headers: new HttpHeaders({
        "cutom-apigw-user-mud-id": mudId,
      }),
    };
    return this.http.get<StandardValues>(GET_STANDARD_VALUES, headers).pipe(
      map((response: any) =>  {
        console.log('::::::', response)
        return {
          "gskAccessSourceLocation": [
            {
              "data": "S3",
              "group": null
            },
            {
              "data": "SFTP",
              "group": null
            },
            {
              "data": "GCP",
              "group": null
            }
          ],
          "dataSetTypeIngestion": [
            {
              "data": "Third party will push data into GSK",
              "group": null
            },
            {
              "data": "GSK will pull data",
              "group": null
            }
          ],
          "dataSetType": [
            {
              "data": "Type 1 - Internal",
              "group": null
            },
            {
              "data": "Type 2 - Contract (pre-existing licensed data)",
              "group": null
            },
            {
              "data": "Type 3 - Contract (new data)",
              "group": null
            },
            {
              "data": "Type 4 - Public",
              "group": null
            }
          ],
          "dataRefreshFrequency": [
            {
              "data": "One-time load",
              "group": null
            },
            {
              "data": "Quarterly",
              "group": null
            },
            {
              "data": "Monthly",
              "group": null
            },
            {
              "data": "Weekly",
              "group": null
            },
            {
              "data": "Daily",
              "group": null
            },
            {
              "data": "More frequently than daily",
              "group": null
            }
          ],
          "status": "success"
        }})
    );
  }

   //Get user roloes for restricting user based on role
   getUserRoles(mudId:any) {
    let headers = {
      headers: new HttpHeaders({
        "cutom-apigw-user-mud-id": mudId,
      }),
    };
    return this.http.get<UserRoles>(GET_USER_ROLES, headers).pipe(
      map((response: any) => response)
    );
  }

  back() {
    this.location.back();
  }

  //get domain lineages
  getDomainLineage(name:any, url:any) {
    let data = {
      "app": "DDFCatalogSearch",
      "suggestQuery": "DDF_SciSearch_Domain_Data_Lineage",
      "text": name
    }
    return this.http.post(url, data);
  }

  requesterUser(name:any, url:any) {
    let data = {
      "app": "EnterpriseSearchApp",
      "suggestQuery": "ES_Query_Suggestion_People_GSK",
      "text": name
    }
    return this.http.post(url, data);
  }

  createIngestionForm(data:any) {
    return this.http.post(CREATE_INGESTION_REQUEST, data);
  }
}
