package com.payxpert.connect2pay.client.requests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payxpert.connect2pay.exception.BadRequestException;
import com.payxpert.connect2pay.utils.Utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * Generic API request class.
 * 
 * @author jsh
 * 
 * @param <T>
 *          Real type of the current class used to implement method chaining.
 */
@JsonInclude(value = Include.NON_NULL)
public abstract class GenericRequest<T extends GenericRequest<T>> {
  protected static final Logger logger = LoggerFactory.getLogger(GenericRequest.class);

  public static final String DEFAULT_API_VERSION = "002.03";

  @NotNull
  @NotEmpty
  @MaxLength(8)
  protected String apiVersion = DEFAULT_API_VERSION;

  protected abstract T getThis();

  /**
   * Get the API version used by this request
   * 
   * @return The API version used (by default 002)
   */
  public String getApiVersion() {
    return apiVersion;
  }

  /**
   * Force the API version to use in the request.<br>
   * <strong>Warning:</strong> Use only if you know what you are doing.
   * 
   * @param apiVersion
   *          API version to use
   * @return The current request for method chaining
   */
  public T setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
    return getThis();
  }

  /**
   * Returns the parameters to be used in the request query string. Should be overridden by children classes.
   * 
   * @return A name/value map of parameters
   */
  @JsonIgnore
  public Map<String, String> getRequestParameters() {
    return new HashMap<String, String>();
  }

  /**
   * This method validates the current request. It checks that all mandatory parameters are present in the call and do
   * the format checks.
   * 
   * 
   * @throws BadRequestException
   *           when at least one parameter is missing
   */
  public void validate() throws Exception {
    Validator validation = new Validator();
    List<ConstraintViolation> constraintViolations = validation.validate(this);

    if (constraintViolations.size() > 0) {
      StringBuilder error = new StringBuilder("Error validating request: ");
      // Build the error message
      for (ConstraintViolation violation : constraintViolations) {
        error.append(violation.getMessage()).append(" *** ");
      }
      logger.debug(String.format("Request validation error: %s",  error.toString()));

      throw new BadRequestException(error.toString());
    }
  }

  /**
   * Export the current request as a JSON string.
   * 
   * @return The JSON string representation of the request.
   * @throws JsonGenerationException
   * @throws JsonMappingException
   * @throws IOException
   */
  public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
    String json = null;

    ObjectMapper mapper = Utils.getJSONObjectMapper();

    try {
      json = mapper.writeValueAsString(this);
    } catch (JsonGenerationException e) {
      logger.error("Error generating JSON request : " + e.getMessage());
      e.printStackTrace();
      throw e;
    } catch (JsonMappingException e) {
      logger.error("Error mapping JSON request: " + e.getMessage());
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      logger.error("IO Error generating JSON request: " + e.getMessage());
      e.printStackTrace();
      throw e;
    }

    return json;
  }

  protected String limitLength(String value, int length) {
    if (value != null && value.length() > length) {
      value = value.substring(0, length);
    }

    return value;
  }
}
