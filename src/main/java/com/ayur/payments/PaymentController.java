package com.ayur.payments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayur.controller.dto.AppointmentsDTO;

@RestController
public class PaymentController {

    @RequestMapping(value = "/appointment/payment", method = RequestMethod.POST,consumes="application/json")
    public void doPayment(@RequestBody AppointmentsDTO appointmentDTO,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            JavaIntegrationKit integrationKit = new JavaIntegrationKit();
            Map<String, String> values = integrationKit.hashCalMethod(request, response);
            PrintWriter writer = response.getWriter();
            String furl = "http://localhost:8080/ayur/failure";
            String surl = "http://localhost:8080/ayur/success";
    // build HTML code
            String htmlResponse = "<html> <body> \n"
                    + "      \n"
                    + "  \n"
                    + "  <h1>PayUForm </h1>\n"
                    + "  \n" + "<div>"
                    + "        <form id=\"payuform\" action=\"" + values.get("action") + "\"  name=\"payuform\" method=POST >\n"
                    + "      <input type=\"hidden\" name=\"key\" value=" + values.get("key").trim() + ">"
                    + "      <input type=\"hidden\" name=\"hash\" value=" + values.get("hash").trim() + ">"
                    + "      <input type=\"hidden\" name=\"txnid\" value=" + values.get("txnid").trim() + ">"
                    + "      <table>\n"
                    + "        <tr>\n"
                    + "          <td><b>Mandatory Parameters</b></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "         <td>Amount: </td>\n"
                    + "          <td><input name=\"amount\" value='1' /></td>\n"
                    + "          <td>First Name: </td>\n"
                    + "          <td><input name=\"firstname\" id=\"firstname\" value=" + appointmentDTO.getName() + " /></td>\n"
                    + "        <tr>\n"
                    + "          <td>Email: </td>\n"
                    + "          <td><input name=\"email\" id=\"email\" value='babu@gmail.com' /></td>\n"
                    + "          <td>Phone: </td>\n"
                    + "          <td><input name=\"phone\" value=" + appointmentDTO.getMobile() + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Product Info: </td>\n"
                    + "<td><input name=\"productinfo\" value='Ayur' ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Success URI: </td>\n"
                    + "          <td colspan=\"3\"><input name=\"surl\"  size=\"64\" value=" + surl + "></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Failure URI: </td>\n"
                    + "          <td colspan=\"3\"><input name=\"furl\" value=" + furl+ " size=\"64\" ></td>\n"
                    + "        </tr>\n"
                    + "\n"
                    + "        <tr>\n"
                    + "          <td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>\n"
                    + "        </tr>\n"
                    + "             <tr>\n"
                    + "          <td><b>Optional Parameters</b></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Last Name: </td>\n"
                    + "          <td><input name=\"lastname\" id=\"lastname\" value=" + values.get("lastname") + " ></td>\n"
                    + "          <td>Cancel URI: </td>\n"
                    + "          <td><input name=\"curl\" value=" + values.get("curl") + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Address1: </td>\n"
                    + "          <td><input name=\"address1\" value=" + values.get("address1") + " ></td>\n"
                    + "          <td>Address2: </td>\n"
                    + "          <td><input name=\"address2\" value=" + values.get("address2") + " ></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>City: </td>\n"
                    + "          <td><input name=\"city\" value=" + values.get("city") + "></td>\n"
                    + "          <td>State: </td>\n"
                    + "          <td><input name=\"state\" value=" + values.get("state") + "></td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "          <td>Country: </td>\n"
                    + "          <td><input name=\"country\" value=" + values.get("country") + " ></td>\n"
                    + "          <td>Zipcode: </td>\n"
                    + "          <td><input name=\"zipcode\" value=" + values.get("zipcode") + " ></td>\n"
                    + "        </tr>\n"
                    + "          <td>UDF1: </td>\n"
                    + "          <td><input name=\"udf1\" value=" + values.get("udf1") + "></td>\n"
                    + "          <td>UDF2: </td>\n"
                    + "          <td><input name=\"udf2\" value=" + values.get("udf2") + "></td>\n"
                    + " <td><input name=\"hashString\" value=" + values.get("hashString") + "></td>\n"
                    + "          <td>UDF3: </td>\n"
                    + "          <td><input name=\"udf3\" value=" + values.get("udf3") + " ></td>\n"
                    + "          <td>UDF4: </td>\n"
                    + "          <td><input name=\"udf4\" value=" + values.get("udf4") + " ></td>\n"
                    + "          <td>UDF5: </td>\n"
                   + "          <td><input name=\"udf5\" value=" + values.get("udf5") + " ></td>\n"
                     + "          <td>PG: </td>\n"
                   + "          <td><input name=\"pg\" value=" + values.get("pg") + " ></td>\n"
                    + "        <td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>\n"
                    + "      \n"
                    + "    \n"
                    + "      </table>\n"
                    + "    </form>\n"
                    + " <script> "
                    + " document.getElementById(\"payuform\").submit(); "
                    + " </script> "
                    + "       </div>   "
                    + "  \n"
                    + "  </body>\n"
                    + "</html>";
    // return response
            writer.println(htmlResponse);
        }
    
    @RequestMapping(value="/success")
    public void paymentSuccess() {
        System.out.println("Success");
    }
    
    @RequestMapping(value="/failure")
    public void paymentFailed() {
        System.out.println("Failed");
    }

}
