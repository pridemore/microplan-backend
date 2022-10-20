package zw.co.creative.microplanbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentUploadDto {
    public String loan_uniqueRef;

    public String documentNationalId;
    public String nationalIdUpload;

    public String documentPhoto;
    public String payslipUpload;

    public String documentPayslip;
    public String clientPictureUpload;

    public String documentProofOfEmployment;
    public String proofOfEmployemntUpload;

    public String borrowerSignature;

    public String witnessSignature;

    public String witnessSignature2;

    public String documentMarriageCertificate;
    public String marriageCertificateUpload;
    public String mariage_cert;

    public String documentSerialNumber;
    public String serialNumberUpload;
    public String serial_num;

    public String documentInvoice;
    public String invoicepic;
}
