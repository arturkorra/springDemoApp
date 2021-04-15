package com.demo.app.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Class name: ServiceConfig
 *
 * Description: ServiceConfig 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@Configuration
@ComponentScan({ "com.demo.app.service" })
public class ServiceConfig {
}
