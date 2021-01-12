package com.youran.generate

import com.youran.generate.vo.Person
import freemarker.template.Configuration
import freemarker.template.Template
import spock.lang.Specification

class FreemarkExample001Test extends Specification {

    def "generate codeing"() {
        given:
        String str = "hello"
        when:
            String dir="/Users/yanghuai/workspace/yangh-dev/youran/youran-generate-core/src/test/resources/ftl/templete";
            String outDir="/Users/yanghuai/workspace/yangh-dev/youran/youran-generate-core/src/test/resources/ftl/out";
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File(dir));
            Template template = conf.getTemplate("/freemark-demo-001.ftl");

            Person person=new Person();
            person.setId(1);
            person.setName("小明");
            Map root = new HashMap();
            root.put("person", person);
            root.put("tmp","freemark")

            Writer out = new FileWriter(outDir + "/freemarker.html");
            template.process(root, out);
            System.out.println("转换成功");
            out.flush();

        then:
        1==1
    }

}

