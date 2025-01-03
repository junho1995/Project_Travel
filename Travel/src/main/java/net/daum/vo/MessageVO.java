package net.daum.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="message")
@EqualsAndHashCode(of="messageNo")
@SequenceGenerator(
		name="message_no_seq_gename",
		sequenceName="message_no_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class MessageVO {

	@Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="message_no_seq_gename"
            )
    private long messageNo;
	
	private String messageText;

	@ManyToOne
    @JoinColumn(name = "chat_no")
    private ChatVO chatVO;
	
	@CreationTimestamp
	private Timestamp message_time;
	
}
