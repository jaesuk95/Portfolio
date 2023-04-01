package com.portfolio.domain.model.custom;

import com.portfolio.domain.model.attachment.Attachment;
import com.portfolio.domain.model.product.phonecase.PhoneCase;
import com.portfolio.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CustomCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment designImage;

    @Lob
    private String object;
    private boolean supported;
    private boolean openly;

    @ManyToOne(fetch = FetchType.LAZY)
    private PhoneCase phoneCase;

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORIGIN_CUSTOM_CASE_ID")
    private CustomCase originDesign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INHERIT_CUSTOM_CASE_ID")
    private CustomCase inheritDesign;
}
