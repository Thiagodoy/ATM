package br.com.agencialove.tpa.model;

import java.math.BigDecimal;

public class PackageMeasures {
	private String weight;
	private String volume; //in cm³
	private String width;
	private String height;
	private String depth;
	private String diameter;
	private String barcode;
	private String type;

	public String getVolume(final ObjectType type) {
		if(this.volume == null || "".equals(this.volume.trim())) {
			if(type == ObjectType.CYLINDER) {
				final BigDecimal r = new BigDecimal(this.diameter).divide(new BigDecimal("2"));
				final BigDecimal h = new BigDecimal(this.height);

				BigDecimal v = new BigDecimal(Math.PI);
				v = v.multiply(r.pow(2)).multiply(h);

				this.volume = v.toString();
			}else if(type == ObjectType.BOX){
				final BigDecimal w = new BigDecimal(this.width);
				final BigDecimal h = new BigDecimal(this.height);
				final BigDecimal d = new BigDecimal(this.depth);

				final BigDecimal v = w.multiply(h).multiply(d);
				this.volume = v.toString();
			}else
				this.volume = "0";
		}

		return this.volume;
	}
	
	public void setPackage(final String width, final String height, final String depth, final String diameter) {
		this.setWidth(width);
		this.setHeight(height);
		this.setDepth(depth);
		this.setDiameter(diameter);
	}
	
	public String getVolume() {
		return this.volume;
	}
	public void setVolume(final String volume) {
		this.volume = volume;
	}
	public String getBarcode() {
		return this.barcode;
	}
	public void setBarcode(final String barcode) {
		this.barcode = barcode;
	}
	public String getWeight() {
		return this.weight;
	}
	public void setWeight(final String weight) {
		this.weight = weight;
	}
	public String getWidth() {
		return this.width;
	}
	public void setWidth(final String width) {
		this.width = width;
	}
	public String getHeight() {
		return this.height;
	}
	public void setHeight(final String height) {
		this.height = height;
	}
	public String getDepth() {
		return this.depth;
	}
	public void setDepth(final String depth) {
		this.depth = depth;
	}
	public String getDiameter() {
		return this.diameter;
	}
	public void setDiameter(final String diameter) {
		this.diameter = diameter;
	}
	public String getType() {
		return this.type;
	}
	public void setType(final String type) {
		this.type = type;
	}
}
