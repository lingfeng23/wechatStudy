package com.malf.pojo;

import com.malf.pojo.Doctor;
import com.malf.pojo.Nurse;
import com.malf.pojo.Patient;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.print.Doc;
import java.util.Date;

/**
 * @author malf
 * @description 就诊记录
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class MedicalRecord {
	// 主键ID
	private long id;
	// 病人
	private Patient patient;
	// 负责医生
	private Doctor doctor;
	// 负责护士
	private Nurse nurse;
	// 就诊时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date clinicTime;
	// 出院时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dischargeTime;
	//
	//
	
}
