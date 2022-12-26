package com.example.huku.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
        ).authorizeHttpRequests(authz -> authz
                .mvcMatchers("/css/**").permitAll()
                .mvcMatchers("/user/registration").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}





// public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     public void configure(WebSecurity web) {
//         DefaultHttpFirewall firewall = new DefaultHttpFirewall();
//         web.httpFirewall(firewall);
//     }
//     /**
//      * SpringSecurityによる認証を設定
//      * @param http HttpSecurityオブジェクト
//      * @throws Exception 例外
//      */
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.formLogin()
//                 .loginPage("/login").permitAll()
//                 .defaultSuccessUrl("/book")
//                 .and()
//                 .authorizeRequests()
//                 .antMatchers("/css/**").permitAll()
//                 .antMatchers("/registration").permitAll()
//                 .and()
//                 .authorizeRequests().anyRequest().authenticated()
//                 .and()
//                 .logout()
//                 .invalidateHttpSession(true)
//                 .deleteCookies("JSESSIONID");
//     }
//     /**
//      * メモリ上にユーザー・パスワードを格納する処理
//      * @param auth 
//      * @throws Exception
//      */
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         //ユーザー名「user」、パスワード「pass」が入力されたらログイン可能とする
//         //パスワードエンコーダーを利用しないようにするため、パスワードの先頭に{noop}を
//         //指定している
//         auth.inMemoryAuthentication()
//                 .withUser("user").password("{noop}pass").roles("USER");
//     }  
    
    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Override //securityの設定をする
    // protected void configure(HttpSecurity http) throws Exception {

    //     http
    //         .authorizeRequests()//ルール、アクセスポリシーの設定
    //         .antMatchers("/login").permitAll()//loginは認証なしでaccessできる
    //         .antMatchers("/css/**").permitAll()
    //         .anyRequest().authenticated()//↑以外のすべてのURLリクエストをloginしないと見れない
    //         .and()
    //         .formLogin().loginPage("/login").permitAll();
    //         // .defaultSuccessUrl("/hello", true)//ログインが成功したら/helloにいく
    //         // .and()
    //         // .logout()//ログアウトの設定
    //         // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));//logoutのURLを/logoutにする
    // }

    // // @Override //認証方法の設定を行う
    // // protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // //     auth
    // //             //userDetailsServiceを使って、認証を行う
    // //             .userDetailsService(userDetailsService);
    // // }

    // // @Bean
    // // public PasswordEncoder passwordEncoder() {
    // //     return new BCryptPasswordEncoder();
    // // }



// }
