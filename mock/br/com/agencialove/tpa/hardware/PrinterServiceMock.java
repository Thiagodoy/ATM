package br.com.agencialove.tpa.hardware;

import javax.print.PrintException;

import br.com.agencialove.tpa.hardware.printer.PrinterCallback;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class PrinterServiceMock implements IPrinterService {

	@Override
	public void printTicket(final String response) {
//		Platform.runLater(()->{
//			final Alert alert = new Alert(AlertType.INFORMATION);
//
//			final Label label = new Label("A impressora de tickets imprimiu:");
//
//			final TextArea textArea = new TextArea();
//			textArea.setEditable(false);
//			textArea.setWrapText(true);
//			textArea.setText(response);
//
//			textArea.setMaxWidth(Double.MAX_VALUE);
//			textArea.setMaxHeight(Double.MAX_VALUE);
//			GridPane.setVgrow(textArea, Priority.ALWAYS);
//			GridPane.setHgrow(textArea, Priority.ALWAYS);
//
//			final GridPane expContent = new GridPane();
//			expContent.setMaxWidth(Double.MAX_VALUE);
//			expContent.add(label, 0, 0);
//			expContent.add(textArea, 0, 1);
//
//			alert.getDialogPane().setExpandableContent(expContent);
//			alert.setHeight(800);
//
//			alert.showAndWait();
//
//		});
	}

	@Override
	public void printTicket(final byte[] pdf) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Código de pré-postagem: KKKKKKKKKK \r\n");
		sb.append("Contrato: KKKKKKKKKK  \r\n");
		sb.append("Serviço: KKKKKKKKKK \r\n");
		sb.append("=========================").append("\r\n");
		sb.append("= Dados do destinatário =").append("\r\n");
		sb.append("=========================").append("\r\n");
		sb.append("Nome: KKKKKKKKKK \r\n");
		sb.append("Endereço: KKKKKKKKKK , KKKKKKKKKK  KKKKKKKKKK \r\n");
		sb.append("Bairro: KKKKKKKKKK \r\n");
		sb.append("Cidade: KKKKKKKKKK \r\n");
		sb.append("Estado: KKKKKKKKKK \r\n");
		sb.append("CEP: KKKKKKKKKK \r\n");
		sb.append("======================").append("\r\n");
		sb.append("= Dados do remetente =").append("\r\n");
		sb.append("======================").append("\r\n");
		sb.append("Nome: KKKKKKKKKK \r\n");
		sb.append("Endereço: KKKKKKKKKK , KKKKKKKKKK  KKKKKKKKKK \r\n");
		sb.append("Bairro: KKKKKKKKKK \r\n");
		sb.append("Cidade: KKKKKKKKKK \r\n");
		sb.append("Estado: KKKKKKKKKK \r\n");
		sb.append("CEP: KKKKKKKKKK \r\n");
		sb.append("======================").append("\r\n");
		sb.append("= Dados da encomenda =").append("\r\n");
		sb.append("======================").append("\r\n");
		sb.append("Tipo: KKKKKKKKKK \r\n");
		sb.append("Peso: KKKKKKKKKK \r\n");
		sb.append("Altura: KKKKKKKKKK \r\n");
		sb.append("Largura: KKKKKKKKKK \r\n");
		sb.append("Comprimento: KKKKKKKKKK \r\n");
		sb.append("Valor declarado: KKKKKKKKKK \r\n");
		sb.append("Declaração de conteúdo: KKKKKKKKKK \r\n");
		sb.append("=======================").append("\r\n");
		sb.append("= Serviços Adicionais =").append("\r\n");
		sb.append("=======================").append("\r\n");
		sb.append("Mão própria: KKKKKKKKKK \r\n");
		sb.append("Aviso de recebimento: KKKKKKKKKK \r\n");
		sb.append("Declaração de Valor: KKKKKKKKKK \r\n");

		this.printTicket(sb.toString());
	}

	@Override
	public void printLabel(final byte[] pdf, final PrinterCallback callback) throws PrintException {
		//TODO implementar a visualizaçaõ do PDF
	}

	@Override
	public void printLabel(byte[] pdf) throws PrintException {
		// TODO Auto-generated method stub
		
	}

	//	private BufferedImage generateQRCodeImage(final String text, final int width, final int height)
	//			throws WriterException, IOException {
	//		final QRCodeWriter qrCodeWriter = new QRCodeWriter();
	//		final BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	//
	//		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	//	}
	//
	//	private BufferedImage createLabel(final LabelInfo info) throws IOException, WriterException {
	//		final BufferedImage template = ImageIO.read(this.getClass().getResourceAsStream("template.png"));
	//		final BufferedImage imageQrCode = this.generateQRCodeImage(info.getQrCode(), 190, 190);
	//		final BufferedImage barcode1 = this.createBarcode128(HumanReadableLocation.TOP, info.getBarcodePostNumber());
	//		final BufferedImage barcode2 = this.createBarcode128(HumanReadableLocation.NONE, info.getReceiverZipCode());
	//		final BufferedImage formatedLogo = PrinterServiceImpl.resizeImage(ImageIO.read(this.getClass().getResourceAsStream("logo.png")), 150, 150);
	//
	//
	//		final Graphics g = template.getGraphics();
	//
	//		g.drawImage(imageQrCode, 210, -20, null);
	//		g.drawImage(formatedLogo, 0, 0, null);
	//		g.drawImage(barcode1, 25, 255, null);
	//		g.drawImage(barcode2, 25, 605, null);
	//
	//		g.setColor(Color.black);
	//		g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
	//		g.drawString(info.getInvoiceNumber(), 56, 184);
	//		g.drawString(info.getVolume(), 565, 184);
	//		g.drawString(info.getOrderNumber(), 104, 214);
	//
	//		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
	//		g.drawString(info.getContractNumber(), 340, 184);
	//		g.drawString("SEDEX Hoje", 276, 214);
	//		g.drawString(info.getWeight(), 574, 214);
	//
	//		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	//		g.drawString(info.getReceiverName(), 25, 515);
	//		g.drawString(info.getReceiverStreet() + "," + info.getReceiverNumber(), 25, 540);
	//		g.drawString(info.getReceiverNeighborhood(), 25, 565);
	//		final String[] arrayFormatedObservation = WordUtils.wrap(info.getReceiverObs(), 26).split("\n");
	//		for(int x = 0; x < arrayFormatedObservation.length; x++) {
	//			g.drawString(arrayFormatedObservation[x], 365, (672 + x * 20));
	//		}
	//		g.drawString(info.getSenderName(), 140, 785);
	//		g.drawString(info.getSenderStreet() + "," + info.getSenderNumber(), 25, 810);
	//		g.drawString(info.getReceiverCity() + "/" + info.getReceiverState(), 140, 590);
	//		g.drawString(info.getSenderCity() + "/" + info.getSenderState(), 140, 860);
	//
	//
	//		final Map<String,String> additionals = new HashMap<>();
	//		if("S".equals(info.getDeliveryNotice())) additionals.put("AR", "S");
	//		if("S".equals(info.getOnwHands())) additionals.put("MP", "S");
	//		if("S".equals(info.getValueDeclaration())) additionals.put("DV", "S");
	//
	//		final int numberOfAdditionals = additionals.size();
	//
	//		//TODO remake this part
	//		for(int i = 0; i < 8; i++){
	//			if (i > numberOfAdditionals - 1) {
	//				if (i < 4) {
	//					g.drawString("XX", 540, (300 + i * 20));
	//				} else {
	//					g.drawString("XX", 580, (300  + (i - 4) * 20));
	//				}
	//			} else {
	//				if (i < 4) {
	//					g.drawString("YY", 540, (300 + i * 20));
	//				} else {
	//					g.drawString("YY", 580, (300  + (i - 4) * 20));
	//				}
	//			}
	//		}
	//
	//		if (info.getSenderComplement().length() > 0) {
	//			g.drawString(info.getSenderComplement()+ "," + info.getSenderNeighborhood(), 25, 835);
	//		} else {
	//			g.drawString(info.getSenderNeighborhood(), 25, 835);
	//		}
	//
	//		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
	//		g.drawString("Remetente:", 25, 785);
	//		g.drawString(info.getSenderZipCode(), 25, 860);
	//		g.drawString(info.getReceiverZipCode(), 25, 590);
	//
	//		g.dispose();
	//
	//		final BufferedImage ret = PrinterServiceImpl.resizeImage(template, 1000, 1374);
	//		return ret;
	//	}
	//
	//	public static BufferedImage resizeImage(final BufferedImage img, final int newW, final int newH) {
	//		final Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	//		final BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	//
	//		final Graphics2D g2d = dimg.createGraphics();
	//		g2d.drawImage(tmp, 0, 0, null);
	//		g2d.dispose();
	//
	//		return dimg;
	//	}

}
