package school;

public enum Grade {
	// fix grade
	APLUS(4.0), A(4.0), AMINUS(3.7), BPLUS(3.3), B(3.0), BMINUS(2.7), CPLUS(2.3), C(2.0), CMINUS(1.7), DPLUS(1.3), D(
			1.0), DMINUS(.7), F(0.0);

	private double gradeNum;

	private Grade(Double num) {
		this.gradeNum = num;
	}

	public Double getGrade() {
		return this.gradeNum;
	}
}
