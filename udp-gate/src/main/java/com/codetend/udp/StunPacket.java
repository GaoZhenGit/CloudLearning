package com.codetend.udp;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StunPacket {
    private static final byte[] MagicCookie = new byte[]{(byte) 0x21, (byte) 0x12, (byte) 0xA4, (byte) 0x42};

    public static StunPacket parse(byte[] data, int len) {
        if (!isStun(data, 0, len)) {
            return null;
        }
        // Get type field.
        int msgType = get2Bytes(data, 0);
        // Get length field.
        int msgLength = get2Bytes(data, 2);
        if ((msgLength != len - 20) || ((msgLength & 0x03) != 0)) {
            log.error("length field + 20 does not match total size (or it is not multiple of 4 bytes), " +
                    "packet discarded");
            return null;
        }
        int msgMethod = (msgType & 0x000f) | ((msgType & 0x00e0) >> 1) | ((msgType & 0x3E00) >> 2);
        int msgClass = ((data[0] & 0x01) << 1) | ((data[1] & 0x10) >> 4);
        StunPacket stunPacket = new StunPacket();
        int pos = 20;
        // Flags (positions) for special MESSAGE-INTEGRITY and FINGERPRINT
        // attributes.
        boolean hasMessageIntegrity = false;
        boolean hasFingerprint = false;
        // Will point to the beginning of the attribute.
        int fingerprintAttrPos;
        // Holds the value of the FINGERPRINT attribute.
        int fingerprint;

        while (pos + 4 <= len) {
            // Get the attribute type.
            Attribute attrType = Attribute.find(get2Bytes(data, pos));
            // Get the attribute length.
            int attrLength = get2Bytes(data, pos + 2);
            // Ensure the attribute length is not greater than the remaining size.
            if ((pos + 4 + attrLength) > len) {
                log.error("the attribute length exceeds the remaining size, packet discarded");
                return null;
            }
            // FINGERPRINT must be the last attribute.
            if (hasFingerprint) {
                log.error("attribute after FINGERPRINT is not allowed, packet discarded");
                return null;
            }
            // After a MESSAGE-INTEGRITY attribute just FINGERPRINT is allowed.
            if (hasMessageIntegrity && attrType != Attribute.FINGERPRINT) {
                log.error("attribute after MESSAGE-INTEGRITY other than FINGERPRINT is not allowed, " +
                        "packet discarded");
                return null;
            }
            int attrValuePos = pos + 4;
            switch (attrType) {
                case USERNAME:
                    byte[] userNameByte = new byte[attrLength];
                    System.arraycopy(data, attrValuePos, userNameByte, 0, attrLength);
                    stunPacket.username = new String(userNameByte);
                    break;
                case PRIORITY:
                    // Ensure attribute length is 4 bytes.
                    if (attrLength != 4) {
                        log.error("attribute PRIORITY must be 4 bytes length, packet discarded");
                        return null;
                    }
                    stunPacket.priority = get4Bytes(data, attrValuePos);
                    break;

                case MESSAGE_INTEGRITY:
                    // Ensure attribute length is 20 bytes.
                    if (attrLength != 20) {
                        log.error("attribute MESSAGE-INTEGRITY must be 20 bytes length, packet discarded");
                        return null;
                    }

                    hasMessageIntegrity = true;
                    stunPacket.messageIntegrity = data[attrValuePos];
                    break;
                case FINGERPRINT:
                    // Ensure attribute length is 4 bytes.
                    if (attrLength != 4) {
                        log.error("attribute FINGERPRINT must be 4 bytes length, packet discarded");
                        return null;
                    }
                    hasFingerprint = true;
                    break;
            }
            pos = padTo4Bytes(pos + 4 + attrLength);
        }
        return stunPacket;
    }

    public static String getStunUseName(byte[] data, int offset, int len) {
        if (data.length < offset + len) {
            return null;
        }
        if (isStun(data, offset, len)) {
            int pos = 20 + offset;
            while (pos + 4 <= len) {
                // Get the attribute type.
                int attrType = get2Bytes(data, pos);
                // Get the attribute length.
                int attrLength = get2Bytes(data, pos + 2);
                // Ensure the attribute length is not greater than the remaining size.
                if ((pos + 4 + attrLength) > len) {
                    log.error("the attribute length exceeds the remaining size, packet discarded");
                    return null;
                }
                int attrValuePos = pos + 4;
                if (attrType == Attribute.USERNAME.value) {
                    byte[] userNameByte = new byte[attrLength];
                    System.arraycopy(data, attrValuePos, userNameByte, 0, attrLength);
                    return new String(userNameByte);
                }
                pos = padTo4Bytes(pos + 4 + attrLength);
            }
        }
        return null;
    }

    public static boolean isStun(byte[] data, int offset, int len) {
        return (
                // STUN headers are 20 bytes.
                (len >= 20) &&
                        // DOC: https://tools.ietf.org/html/draft-ietf-avtcore-rfc5764-mux-fixes
                        (data[0 + offset] < 3) &&
                        // Magic cookie must match.
                        (data[4 + offset] == MagicCookie[0]) && (data[5 + offset] == MagicCookie[1]) &&
                        (data[6 + offset] == MagicCookie[2]) && (data[7 + offset] == MagicCookie[3])
        );
    }

    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    private static int get2Bytes(byte[] data, int i) {
        return (0xFF & data[i]) << 8 + (0xFF & data[i + 1]);
    }

    private static int get4Bytes(byte[] data, int i) {
        return ((0xFF & data[i]) << 24) + ((0xFF & data[i + 1]) << 16) + ((0xFF & data[i + 2]) << 8) + (0xFF & data[i + 3]);
    }

    private static int padTo4Bytes(int size) {
        // If size is not multiple of 32 bits then pad it.
        if ((size & 0x03) != 0) {
            return (size & 0xFFFFFFFC) + 4;
        } else {
            return size;
        }
    }

    private enum Attribute {
        MAPPED_ADDRESS(0x0001),
        USERNAME(0x0006),
        MESSAGE_INTEGRITY(0x0008),
        ERROR_CODE(0x0009),
        UNKNOWN_ATTRIBUTES(0x000A),
        REALM(0x0014),
        NONCE(0x0015),
        XOR_MAPPED_ADDRESS(0x0020),
        PRIORITY(0x0024),
        USE_CANDIDATE(0x0025),
        SOFTWARE(0x8022),
        ALTERNATE_SERVER(0x8023),
        FINGERPRINT(0x8028),
        ICE_CONTROLLED(0x8029),
        ICE_CONTROLLING(0x802A),
        NOMINATION(0xC00);
        private final int value;

        Attribute(int i) {
            this.value = i;
        }

        int getValue() {
            return this.value;
        }

        static Attribute find(int value) {
            for (Attribute attribute : Attribute.values()) {
                if (attribute.getValue() == value) {
                    return attribute;
                }
            }
            return null;
        }
    }

    private String username;
    private int priority;
    private byte messageIntegrity;

    public static void main(String[] srga) {
        byte[] data = {(byte) 0x01, (byte) 0x02};
        System.out.println(Integer.toHexString(get4Bytes(MagicCookie, 0)));
    }
}
