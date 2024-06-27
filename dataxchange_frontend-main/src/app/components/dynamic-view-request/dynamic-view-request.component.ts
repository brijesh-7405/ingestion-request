import {
  Component,
  Input,
  OnChanges,
  SimpleChanges,
  input,
} from "@angular/core";
import { CreateDomain, IngestionRequestDetails } from "../../models/models";
import { CREATE_INGESTION_CONTROLS } from "../../dynamic-form-controls/create-ingestion-controls";

@Component({
  selector: "app-dynamic-view-request",
  templateUrl: "./dynamic-view-request.component.html",
  styleUrl: "./dynamic-view-request.component.scss",
})
export class DynamicViewRequestComponent implements OnChanges {
  @Input() data: any = [];
  @Input() isExpanded: boolean = true;
  public viewData: any = [];

  createDomainData: CreateDomain[] = JSON.parse(
    JSON.stringify(CREATE_INGESTION_CONTROLS)
  );

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["data"]) this.mapDataForView(changes["data"].currentValue);
  }

  mapDataForView(currentValue: IngestionRequestDetails) {
    if (!currentValue) return;

    this.viewData = this.createDomainData.map((domain) => {
      const data = domain.data.map((param) => {
        let keyData = this.getValueSafely(currentValue, param.key as keyof IngestionRequestDetails);
        if (this.isValidISODate(keyData)) {
          keyData = this.convertDate(keyData);
        } else if (keyData != "N/A" && param.data && param.data.length > 0) {
          if (Array.isArray(keyData)) {
            keyData = keyData
              .map(
                (item) =>
                  param.data?.find((i) => i.value === item)?.name ?? item
              )
              .join(", ");
          } else {
            keyData =
              param.data.find((i) => i.value === keyData)?.name ?? keyData;
          }

          if (typeof keyData === "object" && keyData !== null) {
            const keyDataName = param.data.find(
              (i) => i.value.value === keyData
            )?.name;
            keyData = `${keyDataName} - ${this.convertDate(keyData)}`;
          }
        }
        return { label: param.name, value: keyData };
      }).filter((item) => item !== null && item.value !=='' && item.value.length > 0);
      return { section: domain.header, data };
    });
  }


  private getValueSafely(arr: any, key: keyof IngestionRequestDetails): any {
    if (arr && arr[key] !== null && arr[key] !== undefined) {
      return arr[key];
    }
    return "N/A";
  }

  private isValidISODate(dateString: string): boolean {
    const isoRegex = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}Z$/;
    return isoRegex.test(dateString);
  }

  private convertDate(dateString: string) {
    if (dateString) {
      const date = new Date(dateString);
      const options: Intl.DateTimeFormatOptions = {
        year: "numeric",
        month: "long",
        day: "numeric",
      };
      const formattedDate: string = date.toLocaleDateString("en-GB", options);
      return formattedDate;
    } else {
      return "N/A";
    }
  }
}
