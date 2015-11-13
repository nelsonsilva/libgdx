//
//  Globals.m
//

#import <ObjectAL/ObjectAL/OpenAL/ALTypes.h>


#define EXPORT __attribute__ ((visibility ("default")))

EXPORT ALPoint __natj_inline_alpoint(const float x, const float y, const float z) {
	return alpoint(x, y, z);
}

EXPORT ALVector __natj_inline_alvector(const float x, const float y, const float z) {
	return alvector(x, y, z);
}

EXPORT ALOrientation __natj_inline_alorientation(const float atX, const float atY, const float atZ, const float upX, const float upY, const float upZ) {
	return alorientation(atX, atY, atZ, upX, upY, upZ);
}

EXPORT ALPoint __natj_inline_ALPointMake(float x, float y, float z) {
	return ALPointMake(x, y, z);
}


