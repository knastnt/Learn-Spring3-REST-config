package ru.knastnt.tryrestconfig;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.knastnt.tryrestconfig.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
/**
 * Аннотация AnnotationConfigContextLoader загружает бины аннотированных @Configuration классов.
 *
 * Заметь что WebConfig не включен в тест, потому что он должен исполнияться в Servlet context, which is not provided.
 */
@ContextConfiguration(
		classes = {WebConfig.class},//, PersistenceConfig.class},
		loader = AnnotationConfigContextLoader.class)
class SpringContextIntegrationTest {

	@Test
	void contextLoads() {
	}

}
