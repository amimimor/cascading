/*
 * Copyright (c) 2007-2010 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package cascading.pipe;

import cascading.CascadingException;
import cascading.tuple.Fields;
import cascading.util.Util;

/** Class OperatorException is thrown during field name resolution during planning */
public class OperatorException extends CascadingException
  {
  enum Kind
    {
      argument, grouping, sorting
    }

  private Fields incomingFields;
  private Fields argumentSelector;
  private Fields groupingSelector;
  private Fields sortingSelector;
  private Fields declaredFields;
  private Fields outputSelector;

  /** @see cascading.CascadingException#CascadingException() */
  public OperatorException()
    {
    }

  /**
   * Constructor OperatorException creates a new OperatorException instance.
   *
   * @param pipe   of type Pipe
   * @param string of type String
   */
  public OperatorException( Pipe pipe, String string )
    {
    super( Util.formatTrace( pipe, string ) );
    }

  /**
   * Constructor OperatorException creates a new OperatorException instance.
   *
   * @param pipe      of type Pipe
   * @param string    of type String
   * @param throwable of type Throwable
   */
  public OperatorException( Pipe pipe, String string, Throwable throwable )
    {
    super( Util.formatTrace( pipe, string ), throwable );
    }

  /** @see cascading.CascadingException#CascadingException(String) */
  protected OperatorException( String string )
    {
    super( string );
    }

  /** @see cascading.CascadingException#CascadingException(String, Throwable) */
  protected OperatorException( String string, Throwable throwable )
    {
    super( string, throwable );
    }

  /** @see cascading.CascadingException#CascadingException(Throwable) */
  protected OperatorException( Throwable throwable )
    {
    super( throwable );
    }

  /**
   * Constructor OperatorException creates a new OperatorException instance.
   *
   * @param pipe           of type Pipe
   * @param incomingFields of type Fields
   * @param declaredFields of type Fields
   * @param outputSelector of type Fields
   * @param throwable      of type Throwable
   */
  public OperatorException( Pipe pipe, Fields incomingFields, Fields declaredFields, Fields outputSelector, Throwable throwable )
    {
    super( createMessage( pipe, incomingFields, declaredFields, outputSelector ), throwable );

    this.incomingFields = incomingFields;
    this.declaredFields = declaredFields;
    this.outputSelector = outputSelector;
    }

  /**
   * Constructor OperatorException creates a new OperatorException instance.
   *
   * @param pipe           of type Pipe
   * @param kind           of type Kind
   * @param incomingFields of type Fields
   * @param selectorFields of type Fields
   * @param throwable      of type Throwable
   */
  public OperatorException( Pipe pipe, Kind kind, Fields incomingFields, Fields selectorFields, Throwable throwable )
    {
    super( createMessage( pipe, kind, incomingFields, selectorFields ), throwable );

    this.incomingFields = incomingFields;

    if( kind == Kind.argument )
      this.argumentSelector = selectorFields;
    else if( kind == Kind.grouping )
      this.groupingSelector = selectorFields;
    else
      this.sortingSelector = selectorFields;
    }

  /**
   * Method getIncomingFields returns the incomingFields of this OperatorException object.
   *
   * @return the incomingFields (type Fields) of this OperatorException object.
   */
  public Fields getIncomingFields()
    {
    return incomingFields;
    }

  /**
   * Method getArgumentSelector returns the argumentSelector of this OperatorException object.
   *
   * @return the argumentSelector (type Fields) of this OperatorException object.
   */
  public Fields getArgumentSelector()
    {
    return argumentSelector;
    }

  /**
   * Method getGroupingSelector returns the groupingSelector of this OperatorException object.
   *
   * @return the groupingSelector (type Fields) of this OperatorException object.
   */
  public Fields getGroupingSelector()
    {
    return groupingSelector;
    }

  /**
   * Method getSortingSelector returns the sortingSelector of this OperatorException object.
   *
   * @return the sortingSelector (type Fields) of this OperatorException object.
   */
  public Fields getSortingSelector()
    {
    return sortingSelector;
    }

  /**
   * Method getDeclaredFields returns the declaredFields of this OperatorException object.
   *
   * @return the declaredFields (type Fields) of this OperatorException object.
   */
  public Fields getDeclaredFields()
    {
    return declaredFields;
    }

  /**
   * Method getOutputSelector returns the outputSelector of this OperatorException object.
   *
   * @return the outputSelector (type Fields) of this OperatorException object.
   */
  public Fields getOutputSelector()
    {
    return outputSelector;
    }

  private static String createMessage( Pipe pipe, Fields incomingFields, Fields declaredFields, Fields outputSelector )
    {
    String message = "unable to resolve output selector: " + outputSelector.printVerbose() +
      ", with incoming: " + incomingFields.printVerbose() + " and declared: " + declaredFields.printVerbose();

    return Util.formatTrace( pipe, message );
    }

  private static String createMessage( Pipe pipe, Kind kind, Fields incomingFields, Fields argumentSelector )
    {
    String message = "unable to resolve " + kind + " selector: " + argumentSelector.printVerbose() +
      ", with incoming: " + incomingFields.printVerbose();

    return Util.formatTrace( pipe, message );
    }

  }
