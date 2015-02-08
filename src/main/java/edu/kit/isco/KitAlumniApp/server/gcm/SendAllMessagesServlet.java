/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.kit.isco.KitAlumniApp.server.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * Servlet that adds a new message to all registered devices.
 * <p>
 * This servlet is used just by the browser (i.e., not device).
 */
@SuppressWarnings("serial")
public class SendAllMessagesServlet {

  private static final int MULTICAST_SIZE = 1000;

  private Sender sender;

  private static final Executor threadPool = Executors.newFixedThreadPool(5);
/*
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    sender = newSender(config);
  }
*/
  /**
   * Creates the {@link Sender} based on the servlet settings.
   *//*
  protected Sender newSender(ServletConfig config) {
    String key = (String) config.getServletContext()
        .getAttribute(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
    return new Sender(key);
  }*/

  /**
   * Processes the request to add a new message.
   */
  
  protected void doPost()
      throws IOException {
    List<String> devices = Datastore.getDevices();
    String status;
    if (devices.isEmpty()) {
      status = "Message ignored as there is no device registered!";
    } else {
      // NOTE: check below is for demonstration purposes; a real application
      // could always send a multicast, even for just one recipient
      if (devices.size() == 1) {
        // send a single message using plain post
        String registrationId = devices.get(0);
         Message message = new Message.Builder()
        .collapseKey("job")
        .timeToLive(3)
        .delayWhileIdle(true)
        .dryRun(true)
        .addData("job","Awesome")
        .build();
        Result result = sender.send(message, registrationId, 5);
        status = "Sent message to one device: " + result;
      }
    }
   // req.setAttribute(HomeServlet.ATTRIBUTE_STATUS, status.toString());
   // getServletContext().getRequestDispatcher("/home").forward(req, resp);
  }
}
