package org.provoysa12th.directory.business.templates;

import org.provoysa12th.directory.domain.Unit;

public class UnitTemplate {

	public static enum Type {
		STAKE("Provo Utah YSA 12th Stake", 518468, Unit.Type.Stake),
		WARD_166("Provo YSA 166th Ward", 10448, Unit.Type.Ward),
		WARD_167("Provo YSA 167th Ward", 75612, Unit.Type.Ward),
		WARD_168("Provo YSA 168th Ward", 89923, Unit.Type.Ward),
		WARD_169("Provo YSA 169th Ward", 91200, Unit.Type.Ward),
		WARD_170("Provo YSA 170th Ward", 95176, Unit.Type.Ward),
		WARD_171("Provo YSA 171th Ward", 102571, Unit.Type.Ward),
		WARD_172("Provo YSA 172th Ward", 106836, Unit.Type.Ward),
		WARD_173("Provo YSA 173th Ward", 160717, Unit.Type.Ward),
		WARD_174("Provo YSA 174th Ward", 228095, Unit.Type.Ward),
		WARD_175("Provo YSA 175th Ward", 181463, Unit.Type.Ward),
		WARD_176("Provo YSA 176th Ward", 228109, Unit.Type.Ward),
		WARD_177("Provo YSA 177th Ward", 435023, Unit.Type.Ward);

		private final UnitTemplate template;

		private Type(String name, int unitNumber, Unit.Type type) {
			this.template = new UnitTemplate(name, unitNumber, type);
		}

		public UnitTemplate getTemplate() {
			return template;
		}
	}

	private final String name;
	private final int unitNumber;
	private final Unit.Type type;

	public UnitTemplate(String name, int unitNumber, Unit.Type type) {
		this.name = name;
		this.unitNumber = unitNumber;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getUnitNumber() {
		return unitNumber;
	}

	public Unit.Type getType() {
		return type;
	}

}
