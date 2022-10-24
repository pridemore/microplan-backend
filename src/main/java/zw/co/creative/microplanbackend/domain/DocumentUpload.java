package zw.co.creative.microplanbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUpload {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    public String loanUniqueRef;

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
