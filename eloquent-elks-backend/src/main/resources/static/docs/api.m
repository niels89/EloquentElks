#import "api.h"
#ifndef DEF_APINS0PropertyFetchDTO_M
#define DEF_APINS0PropertyFetchDTO_M

/**
 * (no documentation provided)
 */
@implementation APINS0PropertyFetchDTO

- (void) dealloc
{
  [super dealloc];
}
@end /* implementation APINS0PropertyFetchDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface APINS0PropertyFetchDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface APINS0PropertyFetchDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation APINS0PropertyFetchDTO (JAXB)

/**
 * Read an instance of APINS0PropertyFetchDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of APINS0PropertyFetchDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  APINS0PropertyFetchDTO *_aPINS0PropertyFetchDTO = [[APINS0PropertyFetchDTO alloc] init];
  NS_DURING
  {
    [_aPINS0PropertyFetchDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _aPINS0PropertyFetchDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_aPINS0PropertyFetchDTO autorelease];
  return _aPINS0PropertyFetchDTO;
}

/**
 * Initialize this instance of APINS0PropertyFetchDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of APINS0PropertyFetchDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

}
@end /* implementation APINS0PropertyFetchDTO (JAXB) */

#endif /* DEF_APINS0PropertyFetchDTO_M */
