package br.com.agencialove.tpa.hardware.printer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class BufferedImagePrintable implements Printable, Pageable {
	private PageFormat pf;

	private BufferedImage pImage;

	public BufferedImagePrintable() {
	}

	public BufferedImagePrintable(final PageFormat pf) {
		this.pf = pf;
	}


	public BufferedImagePrintable(final BufferedImage img) {
		this.pImage = img;
	}

	@Override
	public int print(final Graphics g, final PageFormat pf, final int pageIndex) throws PrinterException {
		if (this.pImage != null) {
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(this.pImage, 0, 0, (int) pf.getWidth(), (int) pf.getHeight(), null);
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}

	@Override
	public int getNumberOfPages() {
		return 1;
	}

	@Override
	public PageFormat getPageFormat(final int pageIndex) throws IndexOutOfBoundsException {
		return this.pf;
	}

	@Override
	public Printable getPrintable(final int pageIndex) throws IndexOutOfBoundsException {
		return this;
	}

}