package seedu.address.testutil;

import java.io.File;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Photo;
import seedu.address.model.person.Position;
import seedu.address.model.person.Priority;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.Status;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */

public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@example.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    //@@author sebtsh
    public static final String DEFAULT_COMPANY = "NIL";
    public static final String DEFAULT_POSITION = "NIL";
    public static final String DEFAULT_STATUS = "NIL";
    public static final String DEFAULT_PRIORITY = "L";
    public static final String DEFAULT_NOTE = "NIL";
    //@@author
    public static final String DEFAULT_TAGS = "friends";

    public static final String FILE_SEPERATOR = File.separator;

    public static final String DEFAULT_PHOTO = "data" + FILE_SEPERATOR + "default" + ".jpg";

    private Person person;

    //@@author sebtsh
    public PersonBuilder() {
        try {
            Name defaultName = new Name(DEFAULT_NAME);
            Phone defaultPhone = new Phone(DEFAULT_PHONE);
            Email defaultEmail = new Email(DEFAULT_EMAIL);
            Address defaultAddress = new Address(DEFAULT_ADDRESS);
            Company defaultCompany = new Company(DEFAULT_COMPANY);
            Position defaultPosition = new Position(DEFAULT_POSITION);
            Status defaultStatus = new Status(DEFAULT_STATUS);
            Priority defaultPriority = new Priority(DEFAULT_PRIORITY);
            Note defaultNote = new Note(DEFAULT_NOTE);
            Photo defaultPhoto = new Photo(DEFAULT_PHOTO);
            Set<Tag> defaultTags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
            Set<Relationship> defaultRel = SampleDataUtil.getRelSet();
            this.person = new Person(defaultName, defaultPhone, defaultEmail, defaultAddress, defaultCompany,
                    defaultPosition, defaultStatus, defaultPriority,
                    defaultNote, defaultPhoto, defaultTags, defaultRel);
        } catch (IllegalValueException ive) {
            throw new AssertionError("Default person's values are invalid.");
        }
    }
    //@@author

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(ReadOnlyPerson personToCopy) {
        this.person = new Person(personToCopy);
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        try {
            this.person.setName(new Name(name));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("name is expected to be unique.");
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        try {
            this.person.setTags(SampleDataUtil.getTagSet(tags));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("tags are expected to be unique.");
        }
        return this;
    }

    //@@author huiyiiih
    /**
     * Parses the {@code relation} into a {@code Set<Relationship>} and set it to the {@code Person} that we are
     * building.
     */
    public PersonBuilder withRelation(String... relation) {
        try {
            this.person.setRel(SampleDataUtil.getRelSet(relation));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("relationships are expected to be unique." + ive.getMessage());
        }
        return this;
    }
    //@@author

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        try {
            this.person.setAddress(new Address(address));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("address is expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        try {
            this.person.setPhone(new Phone(phone));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("phone is expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        try {
            this.person.setEmail(new Email(email));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("email is expected to be unique.");
        }
        return this;
    }

    //@@author sebtsh
    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        try {
            this.person.setCompany(new Company(company));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Person} that we are building.
     */
    public PersonBuilder withPosition(String position) {
        try {
            this.person.setPosition(new Position(position));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        try {
            this.person.setStatus(new Status(status));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriority(String priority) {
        try {
            this.person.setPriority(new Priority(priority));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        try {
            this.person.setNote(new Note(note));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }
    //@@author

    /**
     * Sets the {@code Photo} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhoto(String photoUrl) {
        try {
            this.person.setPhoto(new Photo(photoUrl));
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        return this;
    }

    public Person build() {
        return this.person;
    }

}
