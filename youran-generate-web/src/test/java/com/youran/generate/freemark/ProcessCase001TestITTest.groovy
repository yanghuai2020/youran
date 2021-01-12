package com.youran.generate.freemark

import com.youran.generate.pojo.vo.ProgressVO
import com.youran.generate.service.MetaCodeGenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.util.function.Consumer


@ContextConfiguration(classes = AppStart.class)
@SpringBootTest
@ActiveProfiles("local")
class ProcessCase001TestITTest extends Specification {

    @Autowired
    private MetaCodeGenService metaCodeGenService


    def "generate codeing"() {
        given:
        String str = "hello"
        when:
        Consumer<ProgressVO> progressConsumer = { progressVO -> this.replyProgress(progressVO) }
        metaCodeGenService.genCodeZip(1, 1, progressConsumer)
        then:
        1 == 1
    }


    def "replyProgress"(ProgressVO topic) {
        println topic
    }
}

